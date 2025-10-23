package org.example.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.orderservice.dto.ResponseDTO;
import org.example.orderservice.dto.request.CreateOrderRequest;
import org.example.orderservice.dto.request.UpdateStatusOrderRequest;
import org.example.orderservice.dto.response.OrderResponse;
import org.example.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ObjectMapper objectMapper;
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getOrdersByUser(@PathVariable Integer userId) {
        List<OrderResponse> orders = orderService.getUserOrders(userId);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .msg("Lấy đơn hàng của người dùng")
                .data(objectMapper.valueToTree(orders))
                .build();
        return ResponseEntity.ok(responseDTO);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest request) {
        OrderResponse newOrder = orderService.createOrder(request);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .data(objectMapper.valueToTree(newOrder))
                .msg("Tạo đơn hàng thành công")
                .build();
        return ResponseEntity.ok(responseDTO);
    }
    @PatchMapping("/{orderId}/status")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable String orderId,
            @RequestBody UpdateStatusOrderRequest request
    ) {
        OrderResponse updated = orderService.updateStatus(orderId, request);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .data(objectMapper.valueToTree(updated))
                .msg("Cập nhật trạng thái đơn hàng thành công")
                .build();
        return ResponseEntity.ok(responseDTO);
    }
}
