package org.example.orderservice.service;

import org.example.orderservice.dto.request.CreateOrderRequest;
import org.example.orderservice.dto.request.UpdateStatusOrderRequest;
import org.example.orderservice.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request);
    List<OrderResponse> getUserOrders(Integer userId);
    OrderResponse updateStatus(String orderId, UpdateStatusOrderRequest request);
    OrderResponse getOrder(String orderId);
}
