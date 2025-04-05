package com.ecommerce.project.controller;

import com.ecommerce.project.model.CategoryModel;
import com.ecommerce.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class Category {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/public/categories")
    public List<CategoryModel> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping("/api/admin/categories")
    public String addCategory(@RequestBody CategoryModel category) {
        categoryService.addCategory(category);
        return "Category added Successfully";
    }

    @DeleteMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        try{
            String Status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(Status, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

}
