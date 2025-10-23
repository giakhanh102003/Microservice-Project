package org.example.orderservice.dto.request;

import lombok.Data;

import java.util.List;

@Data

public class CreateOrderRequest {
    private Integer userId;
    private List<OrderItemRequest> items;
    private Double totalPrice;

    @Data
    public static class OrderItemRequest {
        private String productId;
        private Integer quantity;
        private Double price;
        private String size;
        private String color;
    }
}
