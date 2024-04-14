package com.teddy_blue.Services;

import com.teddy_blue.dtos.AuthenticationRequest;
import com.teddy_blue.dtos.AuthenticationResponse;
import com.teddy_blue.dtos.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request) throws Exception;

    AuthenticationResponse authenticate(AuthenticationRequest request);
}