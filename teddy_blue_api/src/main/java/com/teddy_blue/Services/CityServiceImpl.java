package com.teddy_blue.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.teddy_blue.Entities.City;
import com.teddy_blue.Entities.CityId;
import com.teddy_blue.Repositories.CityRepository;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> getCityById(CityId cityId) {
        return cityRepository.findById(cityId);
    }

    @Override
    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void deleteCity(CityId cityId) {
        cityRepository.deleteById(cityId);
    }
}
