package org.example.catalogservice.service.impl;

import lombok.AllArgsConstructor;
import org.example.catalogservice.dto.product.request.CreateProductRequest;
import org.example.catalogservice.entity.Product;
import org.example.catalogservice.repository.CategoryRepository;
import org.example.catalogservice.repository.ColorRepository;
import org.example.catalogservice.repository.ProductRepository;
import org.example.catalogservice.repository.SizeRepository;
import org.example.catalogservice.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    @Override
    public Product createProduct(CreateProductRequest request) {
        categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category không tồn tại"));

        // Kiểm tra từng size
        for (String sizeId : request.getSizes()) {
            sizeRepository.findById(sizeId)
                    .orElseThrow(() -> new IllegalArgumentException("Size ID không hợp lệ: " + sizeId));
        }

        // Kiểm tra từng color
        for (String colorId : request.getColors()) {
            colorRepository.findById(colorId)
                    .orElseThrow(() -> new IllegalArgumentException("Color ID không hợp lệ: " + colorId));
        }
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .categoryId(request.getCategoryId())
                .sizes(request.getSizes())
                .colors(request.getColors())
                .createdDate(new Date())
                .modifiedDate(new  Date())
                .build();
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với id: " + id));
    }

    @Override
    public List<Product> getProductsByCategory(String categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}
