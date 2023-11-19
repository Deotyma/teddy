package com.teddy_blue.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teddy_blue.Entities.Annonce;

public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

}
