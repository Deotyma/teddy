package com.teddy_blue.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teddy_blue.Entities.Category;

public interface CategoryRepository
	extends JpaRepository<Category, Long> {

}
