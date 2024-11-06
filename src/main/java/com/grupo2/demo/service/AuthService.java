package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo2.demo.model.User.Client;
import com.grupo2.demo.repository.ClientRepository;

@Service
public class AuthService {
    @Autowired
    private ClientRepository clientRepository;

    public boolean authenticate(String username, String password) {
        Client client = clientRepository.findByNome(username);
        if(client != null) {
            return client.getPassword().equals(password);
        }
        return false;
    }
}