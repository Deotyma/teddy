package com.teddy_blue.dtos;

import java.time.LocalDate;

public class AnnonceItem {

    private Long id;
    private String title;
    private String textAnnonce;
    private String photoLink;
    private LocalDate dateAdded;
    private Long userId;
    private String userEmail; 
    private Long categoryId;
    private String categoryName;
    private Long sharingMethodId;
    private String sharingMethodName;

    public AnnonceItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getSharingMethodId() {
        return sharingMethodId;
    }

    public void setSharingMethodId(Long sharingMethodId) {
        this.sharingMethodId = sharingMethodId;
    }

    public String getSharingMethodName() {
        return sharingMethodName;
    }

    public void setSharingMethodName(String sharingMethodName) {
        this.sharingMethodName = sharingMethodName;
    }

}