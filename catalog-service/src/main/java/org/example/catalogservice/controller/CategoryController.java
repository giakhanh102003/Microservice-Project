package org.example.catalogservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.catalogservice.dto.ResponseDTO;
import org.example.catalogservice.dto.category.request.CreateCategoryRequest;
import org.example.catalogservice.entity.Category;
import org.example.catalogservice.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private ObjectMapper objectMapper;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest request){
        Category category = categoryService.createCategory(request);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .data(objectMapper.valueToTree(category))
                .build();
        return ResponseEntity.ok(responseDTO);
    }
}
