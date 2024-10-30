package com.grupo2.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/teste")
    public String home() {
        return "Bem-vindo ao backend!";
    }
}
