package org.example.catalogservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.catalogservice.dto.ResponseDTO;
import org.example.catalogservice.dto.product.request.CreateProductRequest;
import org.example.catalogservice.entity.Product;
import org.example.catalogservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private ObjectMapper objectMapper;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest request){
        Product product = productService.createProduct(request);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .data(objectMapper.valueToTree(product))
                .build();
        return ResponseEntity.ok(responseDTO);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id){
        Product product = productService.getProductById(id);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .data(objectMapper.valueToTree(product))
                .build();
        return ResponseEntity.ok(responseDTO);

    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        ResponseDTO responseDTO = ResponseDTO.builder()
                .data(objectMapper.valueToTree(products))
                .build();
        return ResponseEntity.ok(responseDTO);
    }
    @GetMapping
    public ResponseEntity<?> getProductByCategory(@RequestParam String categoryId) {
        List<Product> products = productService.getProductsByCategory(categoryId);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .data(objectMapper.valueToTree(products))
                .build();
        return ResponseEntity.ok(responseDTO);
    }
}
