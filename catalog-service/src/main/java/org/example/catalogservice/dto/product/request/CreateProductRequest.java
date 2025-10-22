package org.example.catalogservice.dto.product.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequest {
    private String name;
    private String description;
    private double price;
    private int stock;
    private String categoryId;
    private List<String> sizes;
    private List<String> colors;
}
