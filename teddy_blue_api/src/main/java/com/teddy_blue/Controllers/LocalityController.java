package com.teddy_blue.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teddy_blue.Entities.Locality;
import com.teddy_blue.Entities.LocalityId;
import com.teddy_blue.Services.LocalityService;

@RestController
@RequestMapping("/localities")
public class LocalityController {

    private final LocalityService localityService;

    public LocalityController(LocalityService localityService) {
        this.localityService = localityService;
    }

    @GetMapping
    public List<Locality> getAllCities() {
        return localityService.getAllLocalities();
    }

    @GetMapping("/{zipCode}/{localityName}")
    public ResponseEntity<Locality> getLocalityById(@PathVariable String zipCode, @PathVariable String localityName) {
        return localityService.getLocalityById(new LocalityId(zipCode, localityName))
                .map(locality -> ResponseEntity.ok(locality))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Locality createLocality(@RequestBody Locality locality) {
        return localityService.addOrUpdateLocality(locality);
    }

    @DeleteMapping("/{zipCode}/{localityName}")
    public ResponseEntity<Void> deleteLocality(@PathVariable String zipCode, @PathVariable String localityName) {
        localityService.deleteLocality(new LocalityId(zipCode, localityName));
        return ResponseEntity.ok().build();
    }
}
