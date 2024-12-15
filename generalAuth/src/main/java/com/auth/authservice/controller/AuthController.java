package com.auth.authservice.controller;

import com.auth.authservice.dto.LoginRequestDTO;
import com.auth.authservice.dto.LoginResponseDTO;
import com.auth.authservice.dto.SignupRequestDTO;
import com.auth.authservice.model.User;
import com.auth.authservice.repository.UserRepository;
import com.auth.authservice.security.JwtTokenProvider;
import com.auth.authservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@Valid @RequestBody SignupRequestDTO signUpRequest) {
        return ResponseEntity.ok(authenticationService.register(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDTO> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
        log.info("Authenticating user: {}", loginRequest.getUsername());

        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }
}
