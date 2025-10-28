package org.example.paymentservice.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class FeignAuthRequestInterceptor implements RequestInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // Lấy thông tin xác thực từ request đang được xử lý
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Chỉ xử lý nếu đó là một JWT token đã được xác thực
        if (authentication instanceof JwtAuthenticationToken) {
            final JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;

            // Lấy chuỗi token
            final String token = jwtAuthenticationToken.getToken().getTokenValue();

            // Tự động gắn token vào header "Authorization" của request mà Feign sắp gửi đi
            requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, token));
        }
    }
}


