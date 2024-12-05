package com.grupo2.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.demo.dto.AuthResponse;
import com.grupo2.demo.service.AuthService;
import com.grupo2.demo.dto.EmployeeRequest;
import com.grupo2.demo.dto.EmployeeResponse;
import com.grupo2.demo.model.User.Employee;
import com.grupo2.demo.repository.EmployeeRepository;
import com.grupo2.demo.utils.PasswordGenerator;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    private final AuthService authService;

    public EmployeeService(EmployeeRepository employeeRepository, AuthService authService) {
        this.employeeRepository = employeeRepository;
        this.authService = authService;
    }

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        // ADD: Camada de validacao
        Employee employee = employeeRequest.toEmployee();
        String plainPassword = PasswordGenerator.generatePassword();
        String salt = PasswordGenerator.generateSalt();
        String hashedPassword = PasswordGenerator.hashPassword(plainPassword, salt);
        
        employee.setPassword(hashedPassword);
        employee.setSalt(salt);
        employee.setAtivo(true);
        employee.setDataNascimento(employeeRequest.getDataNascimento());
        Employee savedEmployee = employeeRepository.save(employee);
        return mapToResponse(savedEmployee);
    }

    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com id: " + id));
        return mapToResponse(employee);
    }

    public Employee getEmployeeById2(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com id: " + id));
        return employee;
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
        // Obtém o usuário ativo da sessão
        AuthResponse authResponse = authService.getSession();
        Long activeUserId = authResponse != null ? authResponse.getId() : null;

        // Verifica se o ID do usuário ativo é igual ao ID do funcionário que está sendo
        // excluído
        if (activeUserId != null && activeUserId.equals(id)) {
            throw new RuntimeException("Você não pode excluir seu próprio usuário.");
        }

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
