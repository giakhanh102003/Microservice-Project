package org.example.catalogservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private int stock;
    @Field("categoryID") //tương tự @colum khi làm việc với sql
    private String categoryId;
    private List<String> sizes;
    private List<String> colors;
    @Field("created_date")
    private LocalDate createdDate;
    @Field("modified_date")
    private LocalDate modifiedDate;
}
