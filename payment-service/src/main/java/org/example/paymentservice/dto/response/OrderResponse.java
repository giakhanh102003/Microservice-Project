package org.example.paymentservice.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderResponse {
    private String orderId;
    private Integer userId;
    private Double totalPrice;
    private String status;
    private Date createdAt;
    private List<ItemDTO> items;

    @Data
    public static class ItemDTO {
        private String productId;
        private int quantity;
        private Double price;
    }
}
