package org.example.cartservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemoveItemRequest {
    private String productId;
    private int quantity;
    private String size;
    private String color;
}
