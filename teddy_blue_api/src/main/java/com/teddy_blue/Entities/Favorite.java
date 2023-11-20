package com.teddy_blue.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(FavoriteId.class)
public class Favorite {
    public Favorite() {
    }

    @Id
    private long userId;

    @Id
    private long annonceId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "annonce_id", insertable = false, updatable = false)
    private Annonce annonce;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAnnonceId() {
        return annonceId;
    }

    public void setAnnonceId(long annonceId) {
        this.annonceId = annonceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }
}


