package com.teddy_blue.Entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "annonces")
public class Annonce  extends AbstractEntity {
    
    public Annonce() {
	 this.dateAdded = LocalDate.now(); // Sets the current date
    }
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "text_annonce")
    private String textAnnonce;

    @Column(name = "photo_link")
    private String photoLink;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sharing_method_id")
    private SharingMethod sharingMethod;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    
    @Column(name = "date_added", updatable = false)
    private LocalDate dateAdded;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SharingMethod getSharingMethod() {
        return sharingMethod;
    }

    public void setSharingMethod(SharingMethod sharingMethod) {
        this.sharingMethod = sharingMethod;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    

}
