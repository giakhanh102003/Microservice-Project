package org.example.paymentservice.feign.fallback;

import org.example.paymentservice.dto.ResponseDTO;
import org.example.paymentservice.feign.OrderServiceFeignClient;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceFallback implements OrderServiceFeignClient {
    @Override
    public ResponseDTO getOrderById(String orderId) {
        return ResponseDTO.builder()
                .msg(" Order Service is currently unavailable.")
                .data(null)
                .build();
    }
}
