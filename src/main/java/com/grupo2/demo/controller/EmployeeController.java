package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo2.demo.model.User.Employee;
import com.grupo2.demo.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employers")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employerRepository;

    @GetMapping
    public List<Employee> getAllEmployers() {
        return employerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployerById(@PathVariable Long id) {
        Optional<Employee> employer = employerRepository.findById(id);
        return employer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employee createEmployer(@RequestBody Employee employer) {
        return employerRepository.save(employer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployer(@PathVariable Long id, @RequestBody Employee employerDetails) {
        Optional<Employee> employerOptional = employerRepository.findById(id);

        if (employerOptional.isPresent()) {
            Employee employer = employerOptional.get();

            employer.setNome(employerDetails.getNome());
            employer.setEmail(employerDetails.getEmail());
            employer.setAtivo(true);

            Employee updatedEmployer = employerRepository.save(employer);
            return ResponseEntity.ok(updatedEmployer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable Long id) {
        if (employerRepository.existsById(id)) {
            employerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
