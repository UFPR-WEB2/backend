package com.grupo2.demo.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (authResponse != null) {
            return ResponseEntity.ok(authResponse);
        }
        return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
    }

    @PostMapping("getSession")
    public ResponseEntity<AuthResponse> getSession() {

        AuthResponse authResponse = authService.getSession();

        if(authResponse != null){
            return ResponseEntity.ok(authResponse);
        }

        return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/logout")
    public ResponseEntity logout() {
        authService.logout();
        return ResponseEntity.ok().build();
    }

}