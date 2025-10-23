package org.example.paymentservice.feign;

import org.example.paymentservice.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service")
public interface OrderServiceFeignClient {
    @GetMapping("/order/api/orders/{orderId}")
    ResponseDTO getOrderById(@PathVariable String orderId);
}
