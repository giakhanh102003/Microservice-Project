package org.example.paymentservice.feign;

import org.example.paymentservice.dto.ResponseDTO;
import org.example.paymentservice.feign.fallback.OrderServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//http://<order-service-host>/<path của @FeignClient>/<path của @GetMapping>, nên sử dụng path khi tất cả API của service đích đều có chung tiền tố
@FeignClient(name = "order-service",
        path = "/api/orders",
        fallback = OrderServiceFallback.class)
public interface OrderServiceFeignClient {
    @GetMapping("/{orderId}")
    ResponseDTO getOrderById(@PathVariable String orderId);
}
