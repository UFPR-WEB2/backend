package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.grupo2.demo.dto.AuthResponse;
import com.grupo2.demo.dto.LoginRequest;
import com.grupo2.demo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        AuthResponse authResponse = authService.authenticate(email, password);

        return authResponse;
    }

    @PostMapping("getSession")
    public AuthResponse getSession() {
        return authService.getSession();
    }

}