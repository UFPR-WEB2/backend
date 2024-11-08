package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.demo.dto.CustomerRequest;
import com.grupo2.demo.model.User.Customer;
import com.grupo2.demo.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private boolean validatePassword(String password) {
        if (!password.matches("\\d+")) {
            return false;
        }
        return password.length() >= 4;
    }

    public Customer customerCreate(CustomerRequest usuario) {

        if (!validatePassword(usuario.getPassword())) {
            throw new IllegalArgumentException("Senha inválida");
        }

        Customer customer = usuario.toCustomer();

        customer.setAtivo(true);

        return customerRepository.save(customer);
    }

}
