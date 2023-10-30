package com.teddy_blue.Entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CityId implements Serializable {

   
    private static final long serialVersionUID = 1L;

    public CityId() {}

    public CityId(String zipCode, String city) {
        super();
        this.zipCode = zipCode;
        this.city = city;
    }
    
    private String zipCode;
    private String city;
    
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    
    
    
}
