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
    public Locality addOrUpdateLocality(Locality locality) {
        // Check if a locality with the given zipCode and localityName already exists
        Optional<Locality> existingLocality = localityRepository
                .findByZipCodeAndLocalityName(locality.getZipCode(), locality.getLocalityName());

        if (existingLocality.isPresent()) {
            // If exists, update the existing locality
            Locality updatedLocality = existingLocality.get();
            updatedLocality.setZipCode(locality.getZipCode());
            updatedLocality.setLocalityName(locality.getLocalityName());
            return localityRepository.save(updatedLocality);
        } else {
            // If not exists, create a new locality
            return localityRepository.save(locality);
        }
    }

    @Override
    public void deleteLocality(Long id) {
        localityRepository.deleteById(id);
    }

    @Override
    public Optional<Locality> findByZipCodeAndLocalityName(String zipCode, String localityName) {
        return localityRepository.findByZipCodeAndLocalityName(zipCode, localityName);
    }

    @Override
    public Optional<Locality> updateLocality(Long id,
	    Locality localityDetails) {
	return Optional.empty();
    }
}
