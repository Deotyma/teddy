package com.teddy_blue.Services;

import java.util.List;
import java.util.Optional;

import com.teddy_blue.Entities.Locality;

public interface LocalityService {
    Locality saveLocality(Locality locality);

    Optional<Locality> getLocalityById(Long id);

    List<Locality> getAllLocalities();
 
    Optional<Locality> findByZipCodeAndCityCode(String zipCode, String cityCode);
}
