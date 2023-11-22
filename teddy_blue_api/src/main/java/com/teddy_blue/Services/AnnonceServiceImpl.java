package com.teddy_blue.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
@Transactional
public class AnnonceServiceImpl implements AnnonceService {

    private final AnnonceRepository annonceRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final SharingMethodRepository sharingMethodRepository;
    private final FileStorage storage;

    public AnnonceServiceImpl(FileStorage storage, AnnonceRepository annonceRepository, UserRepository userRepository, CategoryRepository categoryRepository, SharingMethodRepository sharingMethodRepository) {
        this.annonceRepository = annonceRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.sharingMethodRepository = sharingMethodRepository;
        this.storage = storage;
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

    @Override
    public void createAnnonce(AnnonceCreate inputs) {
        Annonce entity = new Annonce();
        entity.setTitle(inputs.getTitle());
        entity.setTextAnnonce(inputs.getTextAnnonce());
        MultipartFile file = inputs.getFile();
	String baseName = UUID.randomUUID().toString();
	String fileName = storage.store(file, baseName);
	entity.setPhotoLink(fileName);
        Optional<User> userOpt = userRepository.findById(inputs.getUserId());
        userOpt.ifPresent(entity::setUser);
        Optional<SharingMethod> sharingMethodOpt = sharingMethodRepository.findById(inputs.getSharingMethodId());
        sharingMethodOpt.ifPresent(entity::setSharingMethod);
        Optional<Category> categoryOpt = categoryRepository.findById(inputs.getCategoryId());
        categoryOpt.ifPresent(entity::setCategory);
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
        Optional<Annonce> annonceOpt = annonceRepository.findById(id);
        if (annonceOpt.isPresent()) {
            Annonce entity = annonceOpt.get();
            entity.setTitle(inputs.getTitle());
            entity.setTextAnnonce(inputs.getTextAnnonce());
            MultipartFile file = inputs.getFile();
            if (file != null) {
                String original = entity.getPhotoLink();
                String baseName = UUID.randomUUID().toString();
                String newFullName = storage.replace(file, baseName, original);
                entity.setPhotoLink(newFullName);
            }
            Optional<User> userOpt = userRepository.findById(inputs.getUserId());
            userOpt.ifPresent(entity::setUser);
            Optional<SharingMethod> sharingMethodOpt = sharingMethodRepository.findById(inputs.getSharingMethodId());
            sharingMethodOpt.ifPresent(entity::setSharingMethod);
            Optional<Category> categoryOpt = categoryRepository.findById(inputs.getCategoryId());
            categoryOpt.ifPresent(entity::setCategory);
            annonceRepository.save(entity);
        }
        else {
            throw new EntityNotFoundException("Annonce with ID " + id + " not found");
        }
    }

    @Transactional
    @Override
    public void deleteAnnonce(Long id) {
        Optional<Annonce> annonceOpt = annonceRepository.findById(id);
        if (annonceOpt.isPresent()) {
            Annonce entity = annonceOpt.get();
            String fullName = entity.getPhotoLink();
            annonceRepository.delete(entity);
            storage.delete(fullName);
        }
        // Handle case where Annonce is not found
        else {
            throw new EntityNotFoundException("Annonce with ID " + id + " not found");
        }
    }
}

