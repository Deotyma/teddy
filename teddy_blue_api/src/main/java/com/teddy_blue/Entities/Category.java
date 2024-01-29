package com.teddy_blue.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends AbstractEntity {
    
    @Column(name = "category_name", updatable = false)
    private String categoryName;
    
    public Category() {

    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Object findById(Long categoryId) {
	return categoryId;
    }


}
