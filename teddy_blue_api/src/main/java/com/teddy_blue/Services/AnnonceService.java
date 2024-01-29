package com.teddy_blue.Services;

import java.util.Optional;

import com.teddy_blue.dtos.AnnonceCreate;
import com.teddy_blue.dtos.AnnonceItem;
import com.teddy_blue.dtos.AnnonceUpdate;

public interface AnnonceService {
    void deleteAnnonce(Long id);
    void updateAnnonce(Long id, AnnonceUpdate inputs);
    void createAnnonce(AnnonceCreate inputs);
    Object getAllAnnonces();
    Optional<AnnonceItem> getAnnonceById(Long id);
}
