package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.demo.model.Fix;
import com.grupo2.demo.repository.FixRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// ADICIONEM METODOS REST PARA O CONTROLLER
@RestController
@RequestMapping("/fix")
public class FixController {
    
    @Autowired
    private FixRepository fix;

    @PostMapping
    public Fix postMethodName(@RequestBody Fix entity) {
        fix.save(entity);
        return entity;
    }
    
}
