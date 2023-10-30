package com.teddy_blue.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teddy_blue.Entities.Category;
import com.teddy_blue.Repositories.CategoryRepository;

@Service
public class CategoryServiceImpl
	implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(
	    CategoryRepository categoryRepository) {
	this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
	return categoryRepository.findAll();
    }

}
