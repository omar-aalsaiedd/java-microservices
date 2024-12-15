package com.auth.authservice.controller;

import com.auth.authservice.model.User;
import com.auth.authservice.repository.UserRepository;
import com.auth.authservice.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @GetMapping("/")
    public String test() {
        return "test";
    }

    @GetMapping("/secured")
    public Long secured(@RequestHeader("Authorization") String token) {
            return userRepository.findByUsername(jwtTokenProvider.getUsernameFromToken(token.substring(7))).get().getId();
    }
}
