package com.teddy_blue.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teddy_blue.Entities.Locality;

public interface LocalityRepository
	extends JpaRepository<Locality, Long> {

    Optional<Locality> findByZipCodeAndLocalityName(
	    String zipCode, String localityName);

}
