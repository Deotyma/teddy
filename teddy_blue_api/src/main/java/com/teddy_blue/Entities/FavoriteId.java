package com.teddy_blue.Entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FavoriteId implements Serializable {
    
	private static final long serialVersionUID = 1L;
        
        @Column(name = "user_id")
	private long userId;
        
        @Column(name = "annonce_id")
        private long annonceId;
        
        public FavoriteId() {
        }
        
        public FavoriteId(long userId, long annonceId) {
    	super();
    	this.userId = userId;
    	this.annonceId = annonceId;
        }
        
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
	
	@Override
	public int hashCode() {
	    return Objects.hash(annonceId, userId);
	}
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    FavoriteId other = (FavoriteId) obj;
	    return (annonceId == other.annonceId)
		    && (userId == other.userId);
	}


}
