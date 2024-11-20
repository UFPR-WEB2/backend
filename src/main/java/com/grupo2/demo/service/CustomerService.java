package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.demo.dto.CustomerRequest;
import com.grupo2.demo.model.User.Customer;
import com.grupo2.demo.repository.CustomerRepository;
import com.grupo2.demo.utils.PasswordGenerator;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmailService emailService;
    public Customer customerCreate(CustomerRequest usuario) {
        try {
            Customer customer = usuario.toCustomer();
            String generatedPassword = PasswordGenerator.generatePassword();
    
            String salt = PasswordGenerator.generateSalt();
            String hashedPassword = PasswordGenerator.hashPassword(generatedPassword, salt);
    
            customer.setPassword(hashedPassword);
            customer.setSalt(salt);
            customer.setAtivo(true);
    
            Customer savedCustomer = customerRepository.save(customer);
    
            String subject = "Bem-vindo ao nosso sistema!";
            String body = "Olá " + customer.getNome() + ",\n\nSua senha de acesso é: " + generatedPassword;
            emailService.sendEmail(customer.getEmail(), subject, body);
    
            return savedCustomer;
        } catch (Exception e) {
            return null;
        }
    }
    
}
