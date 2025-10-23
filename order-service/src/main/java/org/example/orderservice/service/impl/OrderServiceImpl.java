package org.example.orderservice.service.impl;

import lombok.AllArgsConstructor;
import org.example.orderservice.dto.request.CreateOrderRequest;
import org.example.orderservice.dto.request.UpdateStatusOrderRequest;
import org.example.orderservice.dto.response.OrderResponse;
import org.example.orderservice.entity.Order;
import org.example.orderservice.entity.OrderItem;
import org.example.orderservice.enums.OrderStatus;
import org.example.orderservice.mapper.OrderMapper;
import org.example.orderservice.repository.OrderRepository;
import org.example.orderservice.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    @Override
    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        Order order = Order.builder()
                .orderId(UUID.randomUUID().toString())
                .userId(request.getUserId())
                .price(request.getTotalPrice())
                .status(OrderStatus.PENDING)
                .createdAt(new Date())
                .build();

        List<OrderItem> items = request.getItems().stream()
                .map(i -> OrderItem.builder()
                        .id(UUID.randomUUID().toString())
                        .productId(i.getProductId())
                        .quantity(i.getQuantity())
                        .size(i.getSize())
                        .color(i.getColor())
                        .price(i.getPrice())
                        .order(order)
                        .build())
                .collect(Collectors.toList());

        order.setOrderItems(items);
        Order saved = orderRepository.save(order);
        return orderMapper.toResponse(saved);
    }

    @Override
    public List<OrderResponse> getUserOrders(Integer userId) {
        return orderRepository.findByUserId(userId)
                .stream().map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse updateStatus(String orderId, UpdateStatusOrderRequest request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(request.getStatus());
        Order updated = orderRepository.save(order);

        return orderMapper.toResponse(updated);
    }
}
