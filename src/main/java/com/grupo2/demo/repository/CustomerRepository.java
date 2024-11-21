package com.grupo2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo2.demo.model.User.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findByNome(String nome);
    public Customer findByEmail(String email);
}
