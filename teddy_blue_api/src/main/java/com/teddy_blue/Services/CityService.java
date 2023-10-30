package com.teddy_blue.Services;

import java.util.List;
import java.util.Optional;

import com.teddy_blue.Entities.City;
import com.teddy_blue.Entities.CityId;

public interface CityService {

    List<City> getAllCities();

    Optional<City> getCityById(CityId cityId);

    City saveCity(City city);

    void deleteCity(CityId cityId);
}
