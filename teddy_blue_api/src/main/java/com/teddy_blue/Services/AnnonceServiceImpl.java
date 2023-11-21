package com.teddy_blue.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teddy_blue.Entities.Annonce;
import com.teddy_blue.Entities.Category;
import com.teddy_blue.Entities.SharingMethod;
import com.teddy_blue.Entities.User;
import com.teddy_blue.Repositories.AnnonceRepository;
import com.teddy_blue.Repositories.CategoryRepository;
import com.teddy_blue.Repositories.SharingMethodRepository;
import com.teddy_blue.Repositories.UserRepository;
import com.teddy_blue.dtos.AnnonceCreate;
import com.teddy_blue.dtos.AnnonceItem;
import com.teddy_blue.dtos.AnnonceUpdate;

@Service
public class AnnonceServiceImpl implements AnnonceService {

    private final AnnonceRepository annonceRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final SharingMethodRepository sharingMethodRepository;

    @Autowired
    public AnnonceServiceImpl(AnnonceRepository annonceRepository,
                              UserRepository userRepository,
                              CategoryRepository categoryRepository,
                              SharingMethodRepository sharingMethodRepository) {
        this.annonceRepository = annonceRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.sharingMethodRepository = sharingMethodRepository;
    }
    
    private AnnonceItem convertToAnnonceItem(Annonce annonce) {
        AnnonceItem item = new AnnonceItem();
        item.setId(annonce.getId());
        item.setTitle(annonce.getTitle());
        item.setTextAnnonce(annonce.getTextAnnonce());
        item.setPhotoLink(annonce.getPhotoLink());
        
        if (annonce.getUser() != null) {
            item.setUserId(annonce.getUser().getId());
            item.setUserEmail(annonce.getUser().getEmail()); // Assuming User has getEmail method
        }
        if (annonce.getCategory() != null) {
            item.setCategoryId(annonce.getCategory().getId());
            item.setCategoryName(annonce.getCategory().getCategoryName()); // Assuming Category has getName method
        }
        if (annonce.getSharingMethod() != null) {
            item.setSharingMethodId(annonce.getSharingMethod().getId());
            item.setSharingMethodName(annonce.getSharingMethod().getSharingMethodName()); // Assuming SharingMethod has getName method
        }
        return item;
    }

    @Transactional // read only = false
    @Override
    public void createAnnonce (AnnonceCreate inputs) {
        Annonce entity = new Annonce();
        entity.setTitle(inputs.getTitle());
        entity.setTextAnnonce(inputs.getTextAnnonce());
        entity.setPhotoLink(inputs.getPhotoLink());
        User user = userRepository.findById(inputs.getUserId())
                         .orElseThrow(() -> new RuntimeException("User not found"));
        entity.setUser(user);
        SharingMethod sharingMethod = sharingMethodRepository.findById(inputs.getSharingMethodId())
                                   .orElseThrow(() -> new RuntimeException("SharingMethod not found"));
        entity.setSharingMethod(sharingMethod);
        Category category = categoryRepository.findById(inputs.getCategoryId())
                            .orElseThrow(() -> new RuntimeException("Category not found"));
        entity.setCategory(category);
        annonceRepository.save(entity);
    }

    @Override
    public Optional<AnnonceItem> getAnnonceById(Long id) {
        return annonceRepository.findById(id)
            .map(this::convertToAnnonceItem);
    }

    @Override
    public List<AnnonceItem> getAllAnnonces() {
        return annonceRepository.findAll().stream()
            .map(this::convertToAnnonceItem)
            .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void updateAnnonce(Long id, AnnonceUpdate inputs) {
        Annonce entity = annonceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Annonce not found"));

        entity.setTitle(inputs.getTitle());
        entity.setTextAnnonce(inputs.getTextAnnonce());
        entity.setPhotoLink(inputs.getPhotoLink());

        if (inputs.getCategoryId() != null) {
            Category category = categoryRepository.findById(inputs.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
            entity.setCategory(category);
        }

        if (inputs.getSharingMethodId() != null) {
            SharingMethod sharingMethod = sharingMethodRepository.findById(inputs.getSharingMethodId())
                .orElseThrow(() -> new RuntimeException("SharingMethod not found"));
            entity.setSharingMethod(sharingMethod);
        }

        annonceRepository.save(entity);
    }

    @Transactional
    @Override
    public void deleteAnnonce(Long id) {
        Annonce entity = annonceRepository.findById(id).orElseThrow(() -> new RuntimeException("Annonce not found"));
        annonceRepository.delete(entity);
    }

}
