package com.teddy_blue.Entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "localities")
public class Locality implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private LocalityId id;

    public Locality() {
    }

    public LocalityId getId() {
        return id;
    }

    public void setId(LocalityId id) {
        this.id = id;
    }

}

