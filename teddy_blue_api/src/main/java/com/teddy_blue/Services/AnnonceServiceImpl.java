package com.teddy_blue.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.teddy_blue.Entities.Annonce;
import com.teddy_blue.Entities.Category;
import com.teddy_blue.Entities.Locality;
import com.teddy_blue.Entities.SharingMethod;
import com.teddy_blue.Entities.User;
import com.teddy_blue.Repositories.AnnonceRepository;
import com.teddy_blue.Repositories.CategoryRepository;
import com.teddy_blue.Repositories.LocalityRepository;
import com.teddy_blue.Repositories.SharingMethodRepository;
import com.teddy_blue.Repositories.UserRepository;
import com.teddy_blue.dtos.AnnonceCreate;
import com.teddy_blue.dtos.AnnonceItem;
import com.teddy_blue.dtos.AnnonceUpdate;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class AnnonceServiceImpl implements AnnonceService {

    private final AnnonceRepository annonceRepository;
    private final LocalityRepository localityRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final SharingMethodRepository sharingMethodRepository;
    private final FileStorage fileStorage;

    public AnnonceServiceImpl(FileStorage fileStorage, AnnonceRepository annonceRepository,LocalityRepository localityRepository, UserRepository userRepository, CategoryRepository categoryRepository, SharingMethodRepository sharingMethodRepository) {
        this.annonceRepository = annonceRepository;
        this.localityRepository = localityRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.sharingMethodRepository = sharingMethodRepository;
        this.fileStorage = fileStorage;
    }
    
    private AnnonceItem convertToAnnonceItem(Annonce annonce) {
        AnnonceItem item = new AnnonceItem();
        item.setId(annonce.getId());
        item.setTitle(annonce.getTitle());
        item.setTextAnnonce(annonce.getTextAnnonce());
        item.setPhotoLink(annonce.getPhotoLink());
        
        if (annonce.getLocality() != null) {
            item.setLocalityId(annonce.getLocality().getId());
            item.setLocalityId(annonce.getLocality().getLocalityId()); // Assuming Locality has getName method
        }
        
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
        MultipartFile file = inputs.getPhotoLink();
        String baseName = UUID.randomUUID().toString();
        String fileName = fileStorage.store(file, baseName);
        entity.setPhotoLink(fileName);
        
        // Set Locality
        Locality locality = localityRepository.findById(inputs.getLocalityId())
            .orElseThrow(() -> new EntityNotFoundException("Locality not found"));
        entity.setLocality(locality);

        // Set User
        User user = userRepository.findById(inputs.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
        entity.setUser(user);

        // Set SharingMethod
        SharingMethod sharingMethod = sharingMethodRepository.findById(inputs.getSharingMethodId())
            .orElseThrow(() -> new EntityNotFoundException("Sharing Method not found"));
        entity.setSharingMethod(sharingMethod);

        // Set Category
        Category category = categoryRepository.findById(inputs.getCategoryId())
            .orElseThrow(() -> new EntityNotFoundException("Category not found"));
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
        Optional<Annonce> annonceOpt = annonceRepository.findById(id);
        if (annonceOpt.isPresent()) {
            Annonce entity = annonceOpt.get();
            entity.setTitle(inputs.getTitle());
            entity.setTextAnnonce(inputs.getTextAnnonce());
	    if ((inputs.getPhotoLink() != null)) {
                String original = entity.getPhotoLink();
                String baseName = UUID.randomUUID().toString();
                String newFullName = fileStorage.replace(inputs.getPhotoLink(), baseName, original);
                entity.setPhotoLink(newFullName);
            }
	 // Set Locality
	 Locality locality = localityRepository.findById(inputs.getLocalityId())
		 .orElseThrow(() -> new EntityNotFoundException("Locality not found"));
	 entity.setLocality(locality);

	        // Set User
	        User user = userRepository.findById(inputs.getUserId())
	            .orElseThrow(() -> new EntityNotFoundException("User not found"));
	        entity.setUser(user);

	        // Set SharingMethod
	        SharingMethod sharingMethod = sharingMethodRepository.findById(inputs.getSharingMethodId())
	            .orElseThrow(() -> new EntityNotFoundException("Sharing Method not found"));
	        entity.setSharingMethod(sharingMethod);

	        // Set Category
	        Category category = categoryRepository.findById(inputs.getCategoryId())
	            .orElseThrow(() -> new EntityNotFoundException("Category not found"));
	        entity.setCategory(category);

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
            fileStorage.delete(fullName);
        }
        else {
            throw new EntityNotFoundException("Annonce with ID " + id + " not found");
        }
    }
}

