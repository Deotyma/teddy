package com.teddy_blue.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teddy_blue.Entities.Role;
import com.teddy_blue.Entities.User;
import com.teddy_blue.Repositories.UserRepository;
import com.teddy_blue.config.JwtService;
import com.teddy_blue.dtos.AuthenticationRequest;
import com.teddy_blue.dtos.AuthenticationResponse;
import com.teddy_blue.dtos.RegisterRequest;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
	this.authenticationManager = authenticationManager;
    }
    
    @Override
    public AuthenticationResponse register(RegisterRequest request) throws Exception {
	    if (userRepository.existsByEmail(request.getEmail())) {
	        throw new Exception("L'email est déjà utilisé.");
	    }

	    User newUser = new User();
	    newUser.setEmail(request.getEmail());
	    newUser.setPassword(passwordEncoder.encode(request.getPassword()));
	    newUser.setFirstName(request.getFirstName());  // Assurez-vous que cette méthode est appelée
	    newUser.setLastName(request.getLastName());    // Assurez-vous que cette méthode est appelée
	    newUser.setNickName(request.getNickName());    // Assurez-vous que cette méthode est appelée
	    newUser.setRole(Role.USER); // Définir le rôle de l'utilisateur

	    User savedUser = userRepository.save(newUser);
	    String jwtToken = jwtService.generateToken(savedUser);
	    return AuthenticationResponse.builder()
	            .accessToken(jwtToken)
	            .build();
	}

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
	    authenticationManager.authenticate(
		    new UsernamePasswordAuthenticationToken(
		            request.getEmail(),
		            request.getPassword()
			    )       
		    );
	    User user = userRepository.findByEmail(request.getEmail())
		        .orElseThrow();
	    String jwtToken = jwtService.generateToken(user);
	    
	    return AuthenticationResponse.builder()
		                .accessToken(jwtToken)
		                .build();
		        
    }
}
