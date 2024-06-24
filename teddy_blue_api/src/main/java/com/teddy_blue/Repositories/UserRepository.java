package com.teddy_blue.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teddy_blue.Entities.User;

public interface UserRepository extends JpaRepository<User, Long>  {

    @SuppressWarnings("null")
    @Override
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

}
