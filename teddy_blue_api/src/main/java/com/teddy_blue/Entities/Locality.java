package com.teddy_blue.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "localities")
public class Locality extends AbstractEntity {

    public Locality() {
    }

    @NotNull
    @Size(max = 5)
    @Column(name = "zip_code", nullable = false, unique = true)
    private String zipCode;

    @NotNull
    @Size(max = 50)
    @Column(name = "city_code", nullable = false, unique = true)
    private String cityCode;
    
    @Column(name = "longitude")
    private Double longitude;
    
    @Column(name = "latitude")
    private Double latitude;
    

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String localityName) {
        this.cityCode = localityName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Long getLocalityId() {
	return id;
    }
    

}

