package com.teddy_blue.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "id_category")
    private String idCategory;

    @Column(name = "category", updatable = false)
    private String category;

    public Category() {

    }

    public String getCategory() {
	return category;
    }

    public void setCategory(String category) {
	this.category = category;
    }

    public String getIdCategory() {
	return idCategory;
    }

    public void setIdCategory(String idCategory) {
	this.idCategory = idCategory;
    }

    @Override
    public String toString() {
	return "Category [idCategory=" + idCategory
		+ ", category=" + category + "]";
    }

}
