package com.teddy_blue.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_categories")
public class Category extends AbstractEntity {
    
    @Column(name = "category", updatable = false)
    private String categoryName;
    
    public Category() {

    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
