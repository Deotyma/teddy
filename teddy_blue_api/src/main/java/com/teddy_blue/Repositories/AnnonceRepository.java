package com.teddy_blue.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teddy_blue.Entities.Annonce;

public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

    Optional<Annonce> findProjectedDetailById(Long id);

}
