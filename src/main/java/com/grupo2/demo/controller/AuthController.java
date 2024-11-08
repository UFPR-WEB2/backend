package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grupo2.demo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if(authService.authenticate(username, password)) {
            return ResponseEntity.ok("Login bem-sucedido");
        }
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
    
}