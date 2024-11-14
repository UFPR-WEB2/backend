package com.grupo2.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo2.demo.model.User.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByNome(String nome);

    Employee findByEmail(String email);

    List<Employee> findByAtivo(boolean ativo);
}
