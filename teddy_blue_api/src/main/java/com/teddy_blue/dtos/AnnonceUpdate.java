package com.teddy_blue.dtos;

import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import com.teddy_blue.dtos.validators.FileSize;
import com.teddy_blue.dtos.validators.MimeType;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class AnnonceUpdate {
    
    private String id;
        
    	@Nullable
        @Size(max = 255)
        private String title;

        @Nullable
        @Size(max = 5000)
        private String textAnnonce;

        @MimeType({ MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE,
	    MediaType.IMAGE_PNG_VALUE })
        @FileSize
        private MultipartFile photoLink;

        @Nullable
        @Positive
        private Long userId;

        @Nullable
        @Positive
        private Long sharingMethodId;

        @Nullable
        @Positive
        private Long categoryId;

        public AnnonceUpdate() {
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

        public MultipartFile getPhotoLink() {
            return photoLink;
        }

        public void setPhotoLink(MultipartFile photoLink) {
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

	public String getId() {
	    return id;
	}

	public void setId(String id) {
	    this.id = id;
	}

}
