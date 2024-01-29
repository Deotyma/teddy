package com.teddy_blue.Entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class LocalityId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "locality_name")
    private String localityName;

    public LocalityId() {
    }

    // Standard getters and setters

    public LocalityId(String zipCode, String localityName) {
        this.zipCode = zipCode;
        this.localityName = localityName;
    }

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

    // Overridden equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocalityId)) return false;
        LocalityId that = (LocalityId) o;
        return Objects.equals(getZipCode(), that.getZipCode()) &&
                Objects.equals(getLocalityName(), that.getLocalityName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getZipCode(), getLocalityName());
    }
}
