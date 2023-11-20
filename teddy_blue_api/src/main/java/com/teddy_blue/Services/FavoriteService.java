package com.teddy_blue.Services;

import java.util.List;

import com.teddy_blue.Entities.Annonce;
import com.teddy_blue.Entities.Favorite;

public interface FavoriteService {
    List<Annonce> getFavoriteAnnoncesByUserId(long userId);
    Favorite addFavorite(long userId, long annonceId);
    void deleteFavorite(long userId, long annonceId);
    long getFavoriteCountByAnnonce(long annonceId);
 
}

