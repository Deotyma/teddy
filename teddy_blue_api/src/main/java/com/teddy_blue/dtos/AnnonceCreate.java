package com.teddy_blue.dtos;

import java.time.LocalDate;

public class AnnonceCreate {

    private String title;
    private String textAnnonce;
    private String photoLink;
    private LocalDate dateAdded;
    private Long userId; // ID of the user associated with the annonce
    private Long sharingMethodId; // ID of the sharing method for the annonce
    private Long categoryId; // ID of the category for the annonce


    public AnnonceCreate() {
	this.setDateAdded(LocalDate.now());
    }

    // Constructor with parameters, if needed

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextAnnonce() {
        return textAnnonce;
    }

    public void setTextAnnonce(String textAnnonce) {
        this.textAnnonce = textAnnonce;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSharingMethodId() {
        return sharingMethodId;
    }

    public void setSharingMethodId(Long sharingMethodId) {
        this.sharingMethodId = sharingMethodId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDate getDateAdded() {
	return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
	this.dateAdded = dateAdded;
    }
}

