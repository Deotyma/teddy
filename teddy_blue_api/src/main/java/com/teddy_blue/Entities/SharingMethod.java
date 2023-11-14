package com.teddy_blue.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sharing_methods")
public class SharingMethod {

    @Id
    private Long id;
    private String sharingMethodName;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSharingMethodName() {
        return sharingMethodName;
    }

    public void setSharingMethodName(String sharingMethodName) {
        this.sharingMethodName = sharingMethodName;
    }
}

