package com.grupo2.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.grupo2.demo.dto.EmployeeRequest;
import com.grupo2.demo.dto.EmployeeResponse;
import com.grupo2.demo.model.User.Employee;
import com.grupo2.demo.repository.EmployeeRepository;

public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        // ADD: Camada de validacao
        Employee employee = employeeRequest.toEmployee();
        employee.setAtivo(true);
        Employee savedEmployee = employeeRepository.save(employee);
        return mapToResponse(savedEmployee);
    }

    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com id: " + id));
        return mapToResponse(employee);
    }

    public EmployeeResponse getEmployeeByName(String nome) {
        Employee employee = employeeRepository.findByNome(nome);
        return mapToResponse(employee);
    }

    public EmployeeResponse getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        return mapToResponse(employee);
    }

    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<EmployeeResponse> getAllActiveEmployees() {
        List<Employee> employees = employeeRepository.findByAtivo(true);
        return employees.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        // ADD: camada de validacao
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com id: " + id));
        employee.setNome(employeeRequest.getNome());
        employee.setEmail(employeeRequest.getEmail());
        employee.setDataNascimento(employeeRequest.getDataNascimento());
        Employee updatedEmployee = employeeRepository.save(employee);
        return mapToResponse(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com id: " + id));

        employee.setAtivo(false);
        employeeRepository.save(employee);
    }

    private EmployeeResponse mapToResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setNome(employee.getNome());
        response.setEmail(employee.getEmail());
        response.setDataNascimento(employee.getDataNascimento());
        response.setAtivo(employee.isAtivo());
        return response;
    }
}