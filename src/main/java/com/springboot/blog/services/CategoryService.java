package com.springboot.blog.services;

import com.springboot.blog.payloads.CategoryDto;
import com.springboot.blog.repositories.CategoryRepo;

import java.util.List;

public interface CategoryService {
    public CategoryDto createCategory(CategoryDto categoryDto);
    public CategoryDto getCategory(int id);
    public List<CategoryDto> getAllCategories();
    public void deleteCategory(int id);
    public CategoryDto updateCategory(CategoryDto categoryDto, int id);
}
