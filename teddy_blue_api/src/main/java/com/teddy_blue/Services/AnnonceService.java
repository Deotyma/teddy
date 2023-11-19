package com.teddy_blue.Services;

import java.util.List;
import java.util.Optional;

import com.teddy_blue.Entities.Annonce;

public interface AnnonceService {
    Annonce createAnnonce(Annonce annonce);
    Optional<Annonce> getAnnonceById(Long id);
    List<Annonce> getAllAnnonces();
    Annonce updateAnnonce(Long id, Annonce annonceDetails);
    void deleteAnnonce(Long id);
}
