package com.springboot.blog.controllers;

import com.springboot.blog.payloads.ApiResponse;
import com.springboot.blog.payloads.CategoryDto;
import com.springboot.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping({"/", ""})
    public ResponseEntity<ApiResponse> postCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto category = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category created successfully", true, "category", category), HttpStatus.CREATED);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<ApiResponse> getCategory(@PathVariable("id") int id) {
        CategoryDto category = this.categoryService.getCategory(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "category", category), HttpStatus.OK);
    }

    @GetMapping({"/", ""})
    public ResponseEntity<ApiResponse> getAllCategory() {
        List<CategoryDto> category = this.categoryService.getAllCategories();
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "categories", category), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") int id) {
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(("Category with id: " + id + " deleted successfully"), true), HttpStatus.OK);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("id") int id, @Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto categoryDto1 = this.categoryService.updateCategory(categoryDto, id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(("Category with id: " + id + " updated successfully"), true, "category", categoryDto1), HttpStatus.OK);
    }
}
