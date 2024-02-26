package com.teddy_blue.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teddy_blue.Entities.Locality;
import com.teddy_blue.Repositories.LocalityRepository;

@Service
public class LocalityServiceImpl implements LocalityService {

    private final LocalityRepository localityRepository;

    @Autowired
    public LocalityServiceImpl(LocalityRepository localityRepository) {
        this.localityRepository = localityRepository;
    }

    @Override
    public Locality saveLocality(Locality locality) {
        return localityRepository.save(locality);
    }

    @Override
    public Optional<Locality> getLocalityById(Long id) {
        return localityRepository.findById(id);
    }

    @Override
    public List<Locality> getAllLocalities() {
        return localityRepository.findAll();
    }

    @Override
    public Optional<Locality> findByZipCodeAndCityCode(String zipCode, String cityCode) {
        return localityRepository.findByZipCodeAndCityCode(zipCode, cityCode);
    }
}
