package com.teddy_blue.Services;

import java.util.List;

import com.teddy_blue.Entities.User;

public interface UserService {
    
    User save(User user);

    User findByPseudoUser(String pseudoUser);

    User findByEmail(String email);

    User findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

}
