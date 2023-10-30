package com.teddy_blue.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teddy_blue.Entities.User;
import com.teddy_blue.Repositories.UserRepository;

@Service

public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
	return userRepository.save(user);
    }

    @Override
    public User findByPseudoUser(String pseudoUser) {
	 return userRepository.findByPseudoUser(pseudoUser);
    }

    @Override
    public User findByEmail(String email) {
	 return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
	return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
	return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
	 userRepository.deleteById(id);

    }

}
