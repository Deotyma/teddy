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
    @Size(max = 255)
    @Column(name = "locality_name", nullable = false, unique = true)
    private String localityName;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

}

