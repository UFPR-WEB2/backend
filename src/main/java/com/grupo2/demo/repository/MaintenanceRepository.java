package com.grupo2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo2.demo.model.Maintenance.Maintenance;

import java.util.List;
import java.util.Optional;

import com.grupo2.demo.model.User.Customer;


@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByCliente(Customer cliente);

    Optional<Maintenance> findByIdAndCliente(Long id, Customer customer);
}
