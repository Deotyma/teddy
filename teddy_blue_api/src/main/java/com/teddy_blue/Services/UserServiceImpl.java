package com.teddy_blue.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.teddy_blue.Entities.Locality;
import com.teddy_blue.Entities.LocalityId;
import com.teddy_blue.Entities.User;
import com.teddy_blue.Repositories.LocalityRepository;
import com.teddy_blue.Repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final LocalityRepository localityRepository;

    public UserServiceImpl(UserRepository userRepository, LocalityRepository localityRepository) {
        this.userRepository = userRepository;
        this.localityRepository = localityRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow();
        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setPassword(userDetails.getPassword());
        user.setNickName(userDetails.getNickName());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUserLocality(Long userId, String zipCode, String localityName) {
        User user = userRepository.findById(userId)
                     .orElseThrow();

        LocalityId localityId = new LocalityId(zipCode, localityName);
        Locality locality = localityRepository.findById(localityId)
                              .orElseGet(() -> {
                                  Locality newLocality = new Locality();
                                  newLocality.setId(localityId);
                                  // Set other necessary fields of Locality, if any
                                  return localityRepository.save(newLocality);
                              });

        user.setLocality(locality);
        return userRepository.save(user);
    }

}
