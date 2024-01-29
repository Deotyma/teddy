package com.teddy_blue.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teddy_blue.Entities.Annonce;
import com.teddy_blue.Entities.Favorite;
import com.teddy_blue.Entities.FavoriteId;
import com.teddy_blue.Entities.User;
import com.teddy_blue.Repositories.AnnonceRepository;
import com.teddy_blue.Repositories.FavoriteRepository;
import com.teddy_blue.Repositories.UserRepository;

@Service
public class FavoriteServiceImpl
	implements FavoriteService {
    
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository; 

    @Autowired
    private AnnonceRepository annonceRepository;

    private User user;

    private Annonce annonce;

    
    @Override
    public List<Annonce> getFavoriteAnnoncesByUserId(long userId) {
	
	userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        List<Favorite> favorites = favoriteRepository.findByUserId(userId);

        return favorites.stream()
                        .map(Favorite::getAnnonce)
                        .collect(Collectors.toList());
    }

    @Override
    public Favorite addFavorite(long userId, long annonceId) {
	    // Check if user and annonce exist
	    userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
	    annonceRepository.findById(annonceId)
	            .orElseThrow(() -> new RuntimeException("Annonce not found with id: " + annonceId));

	    // Check if the favorite already exists
	    FavoriteId favoriteId = new FavoriteId(userId, annonceId);
	    java.util.Optional<Favorite> existingFavorite = favoriteRepository.findById(favoriteId);
	    if (existingFavorite.isPresent()) {
	        throw new RuntimeException("Favorite already exists for user " + userId + " and annonce " + annonceId);
	     	}
	    
	    Favorite favorite = new Favorite();
	        favorite.setUser(user);
	        favorite.setAnnonce(annonce);
	        return favoriteRepository.save(favorite);
    }

    @Override
    public void deleteFavorite(long userId, long annonceId) {
        FavoriteId favoriteId = new FavoriteId(userId, annonceId);
        Favorite favorite = favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new RuntimeException("Favorite not found for user " + userId + " and annonce " + annonceId));
        favoriteRepository.delete(favorite);
    }

    @Override
    public long getFavoriteCountByAnnonce(long annonceId) {
        boolean annonceExists = annonceRepository.existsById(annonceId);
        if (!annonceExists) {
            throw new RuntimeException("Annonce not found with id: " + annonceId);
        }
        return favoriteRepository.countByAnnonceId(annonceId);
    }
}
