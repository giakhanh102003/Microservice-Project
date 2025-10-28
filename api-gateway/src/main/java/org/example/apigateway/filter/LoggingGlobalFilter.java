package org.example.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class LoggingGlobalFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(LoggingGlobalFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        log.info(">>>>> REQUEST START <<<<<");
        log.info(">>>>> URI: {} {}", request.getMethod(), request.getURI());
        log.info(">>>>> Method: {}", request.getMethod());
        log.info(">>>>> Headers: {}", request.getHeaders());

        // Tạo một decorator để can thiệp vào response
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(exchange.getResponse()) {
            @Override
            public Mono<Void> writeWith(org.reactivestreams.Publisher<? extends DataBuffer> body) {
                // Chỉ xử lý nếu body là một Flux (dòng dữ liệu)
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = Flux.from(body);

                    // Sử dụng DataBufferUtils.join để gom tất cả các buffer lại thành một Mono<DataBuffer>
                    return DataBufferUtils.join(fluxBody)
                            .flatMap(dataBuffer -> {
                                // Giờ chúng ta có toàn bộ body trong một DataBuffer duy nhất
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content); // Đọc nội dung để log (tiêu thụ buffer này)

                                // Giải phóng buffer gốc đã được join
                                DataBufferUtils.release(dataBuffer);
                                String responseBody = new String(content, StandardCharsets.UTF_8);

                                // Ghi log
                                log.info("<<<<< RESPONSE START <<<<<");
                                log.info("<<<<< Status Code: {}", getStatusCode());
                                log.info("<<<<< Headers: {}", getHeaders());
                                log.info("<<<<< Body: {}", responseBody);
                                log.info("<<<<< RESPONSE END <<<<<");

                                // Tạo một buffer MỚI với nội dung đã đọc và trả nó về cho client
                                // getDelegate() chính là response gốc
                                return getDelegate().writeWith(Flux.just(bufferFactory().wrap(content)));
                            });
                }
                // Nếu không phải Flux, bỏ qua và ghi như bình thường
                return super.writeWith(body);
            }
        };

        // Thay thế response gốc bằng response đã được decorate
        return chain.filter(exchange.mutate().response(decoratedResponse).build())
                .doOnTerminate(() -> log.info(">>>>> REQUEST END <<<<<"));
    }

    @Override
    public int getOrder() {
        // Đặt thứ tự filter, -1 để chạy trước hầu hết các filter khác
        return -1;
    }
}
