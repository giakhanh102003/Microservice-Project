package org.example.orderservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class OrderResponse {
    private String orderId;
    private Integer userId;
    private Double totalPrice;
    private String status;
    private Date createdAt;
    private List<OrderItemResponse> items;

    @Data
    @Builder
    public static class OrderItemResponse {
        private String productId;
        private Integer quantity;
        private Double price;
    }
}
