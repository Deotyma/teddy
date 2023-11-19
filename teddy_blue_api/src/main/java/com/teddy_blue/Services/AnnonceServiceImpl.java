package com.teddy_blue.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teddy_blue.Entities.Annonce;
import com.teddy_blue.Repositories.AnnonceRepository;
import com.teddy_blue.Repositories.CategoryRepository;
import com.teddy_blue.Repositories.UserRepository;

@Service
public class AnnonceServiceImpl implements AnnonceService {

    private final AnnonceRepository annonceRepository;

    @Autowired
    public AnnonceServiceImpl(AnnonceRepository annonceRepository, UserRepository user, CategoryRepository category) {
        this.annonceRepository = annonceRepository;
        
    }

    @Override
    public Annonce createAnnonce(Annonce annonce) {
	annonce.setDateAdded(LocalDate.now());
        return annonceRepository.save(annonce);
    }

    @Override
    public Optional<Annonce> getAnnonceById(Long id) {
        return annonceRepository.findById(id);
    }

    @Override
    public List<Annonce> getAllAnnonces() {
        return annonceRepository.findAll();
    }

    @Override
    public Annonce updateAnnonce(Long id, Annonce annonceDetails) {
        Annonce annonce = annonceRepository.findById(id)
            .orElseThrow();
        
        if(annonceDetails.getTitle() != null) {
            annonce.setTitle(annonceDetails.getTitle());
        }
        
        if(annonceDetails.getTextAnnonce() != null) {
            annonce.setTitle(annonceDetails.getTextAnnonce());
        }
        
        if(annonceDetails.getPhotoLink() != null) {
            annonce.setTitle(annonceDetails.getPhotoLink());
        }
        
        
        
        return annonceRepository.save(annonce);
    }

    @Override
    public void deleteAnnonce(Long id) {
        Annonce annonce = annonceRepository.findById(id)
            .orElseThrow();
        annonceRepository.delete(annonce);
    }
}
