package com.teddy_blue.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teddy_blue.Entities.Locality;

public interface LocalityRepository
	extends JpaRepository<Locality, Long> {

    Optional<Locality> findByZipCodeAndCityCode(
	    String zipCode, String cityCode);
    
    List<Locality> findByZipCodeContaining(String zipCode);

}
