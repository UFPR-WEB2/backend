package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import com.grupo2.demo.model.User.Costumer;
import com.grupo2.demo.repository.CostumerRepository;
=======

import com.grupo2.demo.model.User.Customer;
import com.grupo2.demo.repository.ClientRepository;
>>>>>>> e24476d9dc39a43b8d0b4952cfa065957f67639d

@Service
public class AuthService {
    @Autowired
    private CostumerRepository clientRepository;

    public boolean authenticate(String username, String password) {
<<<<<<< HEAD
        Costumer client = clientRepository.findByNome(username);
=======
        Customer client = clientRepository.findByNome(username);
>>>>>>> e24476d9dc39a43b8d0b4952cfa065957f67639d
        if(client != null) {
            return client.getPassword().equals(password);
        }
        return false;
    }
}