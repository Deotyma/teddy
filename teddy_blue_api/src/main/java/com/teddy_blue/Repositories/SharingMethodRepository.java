package com.teddy_blue.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teddy_blue.Entities.SharingMethod;

public interface SharingMethodRepository extends JpaRepository<SharingMethod, String> {

    Optional<SharingMethod> findById(Long sharingMethodId);

}

