package org.example.catalogservice.service;

import org.example.catalogservice.dto.category.request.CreateCategoryRequest;
import org.example.catalogservice.entity.Category;

public interface CategoryService {
    Category createCategory(CreateCategoryRequest request);

}
