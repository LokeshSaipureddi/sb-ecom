package com.ecommerce.project.service;

import com.ecommerce.project.model.CategoryModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private List<CategoryModel> categories = new ArrayList<>();
    private Long nxtId = 1L;

    @Override
    public List<CategoryModel> getCategories() {
        return categories;
    }

    @Override
    public void addCategory(CategoryModel category) {
        category.setCategoryId(nxtId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId){
        CategoryModel category = categories.stream()
                        .filter(c -> c.getCategoryId().equals(categoryId))
                                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        categories.remove(category);
        return "Category" + categoryId + "deleted Successfully";
    }
}
