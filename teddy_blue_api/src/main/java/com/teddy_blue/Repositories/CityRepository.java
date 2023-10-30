package com.teddy_blue.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teddy_blue.Entities.City;
import com.teddy_blue.Entities.CityId;

public interface CityRepository
	extends JpaRepository<City, CityId> {

}
