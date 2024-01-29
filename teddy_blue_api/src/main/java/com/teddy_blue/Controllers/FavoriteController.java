package com.teddy_blue.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teddy_blue.Entities.Annonce;
import com.teddy_blue.Entities.Favorite;
import com.teddy_blue.Services.FavoriteService;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    // Endpoint to get all favorite annonces for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Annonce>> getFavoriteAnnoncesByUserId(@PathVariable int userId) {
        try {
            List<Annonce> annonces = favoriteService.getFavoriteAnnoncesByUserId(userId);
            return ResponseEntity.ok(annonces);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Endpoint to add a favorite annonce for a user
    @PostMapping("/add")
    public ResponseEntity<Favorite> addFavorite(@RequestParam int userId, @RequestParam int annonceId) {
        try {
            Favorite favorite = favoriteService.addFavorite(userId, annonceId);
            return ResponseEntity.ok(favorite);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Endpoint to delete a favorite annonce for a user
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFavorite(@RequestParam int userId, @RequestParam int annonceId) {
        try {
            favoriteService.deleteFavorite(userId, annonceId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint to get the count of users who have added a specific annonce to their favorites
    @GetMapping("/annonce/{annonceId}/count")
    public ResponseEntity<Long> getFavoriteCountByAnnonce(@PathVariable int annonceId) {
        try {
            long count = favoriteService.getFavoriteCountByAnnonce(annonceId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}