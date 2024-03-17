package com.springboot.blog.services.impl;

import com.springboot.blog.entities.Category;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.payloads.CategoryDto;
import com.springboot.blog.repositories.CategoryRepo;
import com.springboot.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.categoryRepo.save(this.dtoToCat(categoryDto));
        return this.catToDto(category);
    }

    @Override
    public CategoryDto getCategory(int id) {
        Category category = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found", "id", id));
        return this.catToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = this.categoryRepo.findAll();
        return categoryList.stream().map(this::catToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(int id) {
        Category category = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found", "id", id));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int id) {
        Category category = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found", "id", id));
        categoryDto.setId(id);
        Category category1 = this.categoryRepo.save(this.dtoToCat(categoryDto));
        return this.catToDto(category1);
    }

    private Category dtoToCat(CategoryDto categoryDto)
    {
        return this.modelMapper.map(categoryDto, Category.class);
    }
    private CategoryDto catToDto(Category category)
    {
        return this.modelMapper.map(category, CategoryDto.class);
    }

}
