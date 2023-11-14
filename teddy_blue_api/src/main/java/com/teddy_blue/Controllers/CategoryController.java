package com.teddy_blue.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teddy_blue.Entities.Category;
import com.teddy_blue.Services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(
	    CategoryService categoryService) {
	this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
	return categoryService.findAll();
    }
}
