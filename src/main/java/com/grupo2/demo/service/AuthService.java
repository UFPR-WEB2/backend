package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo2.demo.model.User.Costumer;
import com.grupo2.demo.repository.CostumerRepository;

@Service
public class AuthService {
    @Autowired
    private CostumerRepository clientRepository;

    public boolean authenticate(String username, String password) {
        Costumer client = clientRepository.findByNome(username);
        if(client != null) {
            return client.getPassword().equals(password);
        }
        return false;
    }
}