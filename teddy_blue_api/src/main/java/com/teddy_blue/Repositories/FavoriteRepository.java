package com.teddy_blue.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teddy_blue.Entities.Favorite;
import com.teddy_blue.Entities.FavoriteId;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {

    List<Favorite> findByUserId(long userId);

    int countByAnnonceId(long annonceId);

}

