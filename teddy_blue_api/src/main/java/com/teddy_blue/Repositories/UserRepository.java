package com.teddy_blue.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teddy_blue.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByPseudoUser(String pseudoUser);
    User findByEmail(String email);

}
