package com.auth.authservice.service;

import com.auth.authservice.dto.LoginRequestDTO;
import com.auth.authservice.dto.LoginResponseDTO;
import com.auth.authservice.dto.SignupRequestDTO;
import com.auth.authservice.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface AuthenticationService {

    public User register(SignupRequestDTO signupRequestDTO);

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
