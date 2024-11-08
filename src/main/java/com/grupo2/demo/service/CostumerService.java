package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.demo.dto.CustomerRequest;
import com.grupo2.demo.model.User.Costumer;
import com.grupo2.demo.repository.CostumerRepository;

@Service
public class CostumerService {

    @Autowired
    private CostumerRepository costumerRepository;

    private boolean validatePassword(String password) {
        if (!password.matches("\\d+")) {
            return false;
        }
        return password.length() >= 4;
    }

    public Costumer costumerCreate(CustomerRequest usuario) {

        if (!validatePassword(usuario.getPassword())) {
            throw new IllegalArgumentException("Senha inválida");
        }

        Costumer costumer = usuario.toCustomer();

        // add: conversão para Costumer

        return costumerRepository.save(usuario);
    }

}
