package com.teddy_blue.Services;

import java.util.List;
import java.util.Optional;

import com.teddy_blue.Entities.Locality;

public interface LocalityService {
    Locality saveLocality(Locality locality);

    Optional<Locality> getLocalityById(Long id);

    List<Locality> getAllLocalities();

    Locality addOrUpdateLocality(Locality locality);

    void deleteLocality(Long id);
    
    Optional<Locality> findByZipCodeAndLocalityName(String zipCode, String localityName);

    Optional<Locality> updateLocality(Long id,
	    Locality localityDetails);
}
