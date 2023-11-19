package com.teddy_blue.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teddy_blue.Entities.Annonce;
import com.teddy_blue.Services.AnnonceService;

@RestController
@RequestMapping("/annonces")
public class AnnonceController {

    private final AnnonceService annonceService;

    @Autowired
    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    // Create a new annonce
    @PostMapping
    public Annonce createAnnonce(@RequestBody Annonce annonce) {
        return annonceService.createAnnonce(annonce);
    }

    // Get all annonces
    @GetMapping
    public List<Annonce> getAllAnnonces() {
        return annonceService.getAllAnnonces();
    }

    // Get annonce by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Annonce>> getAnnonceById(@PathVariable Long id) {
        Optional<Annonce> annonce = annonceService.getAnnonceById(id);
        return ResponseEntity.ok().body(annonce);
    }

    // Update annonce
    @PutMapping("/{id}")
    public ResponseEntity<Annonce> updateAnnonce(@PathVariable Long id, @RequestBody Annonce annonceDetails) {
        Annonce updatedAnnonce = annonceService.updateAnnonce(id, annonceDetails);
        return ResponseEntity.ok(updatedAnnonce);
    }

    // Delete annonce
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnonce(@PathVariable Long id) {
        annonceService.deleteAnnonce(id);
        return ResponseEntity.ok().build();
    }
}
