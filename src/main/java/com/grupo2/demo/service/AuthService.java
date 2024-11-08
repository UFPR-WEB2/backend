package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo2.demo.model.User.Customer;
import com.grupo2.demo.repository.CustomerRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {
    @Autowired
    private CustomerRepository clientRepository;

    @Autowired
    private HttpSession session;

    public boolean authenticate(String username, String password) {
        Customer client = clientRepository.findByNome(username);
        if(client != null) {
            if(client.getPassword().equals(password)) {
                session.setAttribute("user", client);
                return true;
            }
        }
        return false;
    }
}