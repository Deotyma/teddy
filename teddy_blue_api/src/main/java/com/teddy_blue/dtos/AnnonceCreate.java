package com.teddy_blue.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.teddy_blue.dtos.validators.FileSize;
import com.teddy_blue.dtos.validators.MimeType;

public class AnnonceCreate {

    @NotBlank
    @Size(max = 255)
    private String title;
    
    @NotBlank
    @Size(max = 5000)
    private String textAnnonce;

    @NotNull
    @MimeType({ MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE,
	    MediaType.IMAGE_PNG_VALUE })
    @FileSize
    private MultipartFile photoLink;
    
    private LocalDate dateAdded;
    
    @NotNull
    @Positive
    private Long userId; 
    
    @NotNull
    @Positive
    private Long sharingMethodId;
    
    @NotNull
    @Positive
    private Long categoryId;


    public AnnonceCreate() {
	this.setDateAdded(LocalDate.now());
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

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDate getDateAdded() {
	return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
	this.dateAdded = dateAdded;
    }

    public Long getCategoryId() {
	return categoryId;
    }

    public MultipartFile getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(MultipartFile photoLink) {
        this.photoLink = photoLink;
    }
}

