package com.teddy_blue.dtos;

public class AnnonceUpdate {

    private String title;
    private String textAnnonce;
    private String photoLink;
    private Long categoryId;
    private Long sharingMethodId;
   
    public AnnonceUpdate() {
    }

    // Getters and Setters
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSharingMethodId() {
        return sharingMethodId;
    }

    public void setSharingMethodId(Long sharingMethodId) {
        this.sharingMethodId = sharingMethodId;
    }

}

