package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo2.demo.model.User.Costumer;
import com.grupo2.demo.repository.CostumerRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {
    @Autowired
    private CostumerRepository clientRepository;

    @Autowired
    private HttpSession session;

    public boolean authenticate(String username, String password) {
        Costumer client = clientRepository.findByNome(username);
        if(client != null) {
            if(client.getPassword().equals(password)) {
                session.setAttribute("user", client);
                return true;
            }
        }
        return false;
    }
}