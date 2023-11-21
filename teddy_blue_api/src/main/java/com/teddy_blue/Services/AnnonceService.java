package com.teddy_blue.Services;

import java.util.List;
import java.util.Optional;

import com.teddy_blue.dtos.AnnonceCreate;
import com.teddy_blue.dtos.AnnonceItem;
import com.teddy_blue.dtos.AnnonceUpdate;

public interface AnnonceService {
    Optional<AnnonceItem> getAnnonceById(Long id);
    List<AnnonceItem> getAllAnnonces();
    void deleteAnnonce(Long id);
    void updateAnnonce(Long id, AnnonceUpdate inputs);
    void createAnnonce(AnnonceCreate inputs);
}
