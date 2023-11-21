package com.teddy_blue.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teddy_blue.Entities.User;

public interface UserRepository extends JpaRepository<User, Long>  {

    @Override
    Optional<User> findById(Long id);

}
