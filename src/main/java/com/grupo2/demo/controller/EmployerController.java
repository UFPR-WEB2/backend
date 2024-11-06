package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grupo2.demo.model.Employer;
import com.grupo2.demo.repository.EmployerRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employers")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping
    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employer> getEmployerById(@PathVariable Long id) {
        Optional<Employer> employer = employerRepository.findById(id);
        return employer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employer createEmployer(@RequestBody Employer employer) {
        return employerRepository.save(employer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employer> updateEmployer(@PathVariable Long id, @RequestBody Employer employerDetails) {
        Optional<Employer> employerOptional = employerRepository.findById(id);

        if (employerOptional.isPresent()) {
            Employer employer = employerOptional.get();

            employer.setNome(employerDetails.getNome());
            employer.setEmail(employerDetails.getEmail());
            employer.setAtivo(true);

            Employer updatedEmployer = employerRepository.save(employer);
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
