package com.teddy_blue.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends AbstractEntity {
    
    @Column(name = "category_name", updatable = false)
    private String category_name;
    
    public Category() {

    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

}
