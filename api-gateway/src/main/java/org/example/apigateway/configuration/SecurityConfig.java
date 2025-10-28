package org.example.apigateway.configuration;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import javax.crypto.SecretKey;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Value("${jwt.secret}") // Đọc key từ file .yml
    private String secretKey;

    // Bước 1: Tạo Bean SecretKey để giải mã
    @Bean
    public SecretKey secretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Cung cấp bộ giải mã JWT cho Spring Security
    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() {
        return NimbusReactiveJwtDecoder.withSecretKey(secretKey()).build();
    }

    // Bước 2 & 3: Thiết lập luật lệ và cấu hình
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // Tắt CSRF
                .authorizeExchange(exchange -> exchange
                        // Cho phép các endpoint của auth-service đi qua
                        .pathMatchers("/auth/api/auth/**").permitAll()
                        // Mọi endpoint khác đều yêu cầu xác thực
                        .anyExchange().authenticated()
                )
                // Kích hoạt cơ chế xác thực JWT
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtDecoder(reactiveJwtDecoder())));

        return http.build();
    }
}
