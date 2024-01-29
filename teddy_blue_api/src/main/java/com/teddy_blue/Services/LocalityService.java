package com.teddy_blue.Services;

import java.util.List;
import java.util.Optional;

import com.teddy_blue.Entities.Locality;
import com.teddy_blue.Entities.LocalityId;

public interface LocalityService {
    Locality saveLocality(Locality locality);

    Optional<Locality> getLocalityById(LocalityId id);

    List<Locality> getAllLocalities();

    Locality addOrUpdateLocality(Locality locality);

    void deleteLocality(LocalityId id);
}
