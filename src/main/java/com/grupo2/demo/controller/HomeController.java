package com.grupo2.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/teste")
    public String home() {
        return "Bem-vindo ao backend!";
    }
}
