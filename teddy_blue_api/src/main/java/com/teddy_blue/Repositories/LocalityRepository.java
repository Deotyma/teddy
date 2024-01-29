package com.teddy_blue.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teddy_blue.Entities.Locality;
import com.teddy_blue.Entities.LocalityId;

public interface LocalityRepository
	extends JpaRepository<Locality, LocalityId> {

}
