package org.example.catalogservice.service;

import org.example.catalogservice.dto.product.request.CreateProductRequest;
import org.example.catalogservice.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(CreateProductRequest request);
    List<Product> getAllProducts();
    Product getProductById(String id);
    List<Product> getProductsByCategory(String categoryId);
}
