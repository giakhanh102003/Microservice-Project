package org.example.catalogservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.catalogservice.dto.category.request.CreateCategoryRequest;
import org.example.catalogservice.entity.Category;
import org.example.catalogservice.repository.CategoryRepository;
import org.example.catalogservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    @Transactional
    public Category createCategory(CreateCategoryRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createdDate(new Date())
                .build();
        return categoryRepository.save(category);
    }
}
