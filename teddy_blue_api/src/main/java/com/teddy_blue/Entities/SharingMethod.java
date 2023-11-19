package com.teddy_blue.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sharing_methods")
public class SharingMethod extends AbstractEntity{

    @Column(name = "sharing_method_name", updatable = false)
    private String sharingMethodName;

    // Getters and setters

    public String getSharingMethodName() {
        return sharingMethodName;
    }

    public void setSharingMethodName(String sharingMethodName) {
        this.sharingMethodName = sharingMethodName;
    }
}

