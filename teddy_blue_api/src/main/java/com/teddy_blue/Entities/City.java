package com.teddy_blue.Entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City {

    public City() {
	super();
    }

    @EmbeddedId
    private CityId id;

    public CityId getId() {
        return id;
    }

    public void setId(CityId id) {
        this.id = id;
    }

    @Override
    public String toString() {
	return "City [id=" + id + "]";
    }

}

