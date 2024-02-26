package com.teddy_blue.Services;

import java.util.List;
import java.util.Optional;

import com.teddy_blue.Entities.User;

public interface UserService {
    
    User saveUser(User user);

    Optional<User> getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(Long id, User user);

    void deleteUser(Long id);

}
