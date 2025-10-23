package org.example.cartservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "user_carts")
public class Cart {
    @Id
    private String id;

    private Integer userId;

    private List<CartItem> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CartItem {
        private String productId;
        private int quantity;
        private String size;
        private String color;
    }
}

