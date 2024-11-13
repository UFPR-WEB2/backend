package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo2.demo.dto.LoginRequest;
import com.grupo2.demo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        if(authService.authenticate(email, password)) {
            return ResponseEntity.status(200).body("Login efetuado com sucesso");
        }
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}