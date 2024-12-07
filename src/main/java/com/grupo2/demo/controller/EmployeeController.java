package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo2.demo.dto.EmployeeRequest;
import com.grupo2.demo.dto.EmployeeResponse;
import com.grupo2.demo.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeResponse> getAllEmployers() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/active")
    public List<EmployeeResponse> getAllActiveEmployees() {
        return employeeService.getAllActiveEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployerById(@PathVariable Long id) {
        try {
            EmployeeResponse employer = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployer(@RequestBody EmployeeRequest employerRequest) {
        EmployeeResponse newEmployer = employeeService.createEmployee(employerRequest);
        return ResponseEntity.ok(newEmployer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployer(@PathVariable Long id, @RequestBody EmployeeRequest employerRequest) {
        try {
            EmployeeResponse updatedEmployer = employeeService.updateEmployee(id, employerRequest);
            return ResponseEntity.ok(updatedEmployer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeResponse> deactivateEmployer(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
