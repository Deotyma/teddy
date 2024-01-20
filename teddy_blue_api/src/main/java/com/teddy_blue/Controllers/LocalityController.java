package com.teddy_blue.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teddy_blue.Entities.Locality;
import com.teddy_blue.Services.LocalityService;

@RestController
@RequestMapping("/localities")
public class LocalityController {

    private final LocalityService localityService;

    public LocalityController(LocalityService localityService) {
        this.localityService = localityService;
    }

    @GetMapping
    public List<Locality> getAllLocalities() {
        return localityService.getAllLocalities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Locality> getLocalityById(@PathVariable Long id) {
        return localityService.getLocalityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Locality createLocality(@RequestBody Locality locality) {
        return localityService.saveLocality(locality);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locality> updateLocality(@PathVariable Long id, @RequestBody Locality localityDetails) {
        return localityService.updateLocality(id, localityDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteLocality(@PathVariable Long id) {
        try {
            localityService.deleteLocality(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

