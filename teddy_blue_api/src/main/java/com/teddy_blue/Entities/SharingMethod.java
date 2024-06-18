package com.teddy_blue.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_sharing_methods")
public class SharingMethod extends AbstractEntity{

    @Column(name = "sharing_method", updatable = false)
    private String sharingMethodName;

    // Getters and setters

    public String getSharingMethodName() {
        return sharingMethodName;
    }

    public void setSharingMethodName(String sharingMethodName) {
        this.sharingMethodName = sharingMethodName;
    }
}

