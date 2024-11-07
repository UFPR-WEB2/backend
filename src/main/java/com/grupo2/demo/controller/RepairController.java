package com.grupo2.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grupo2.demo.model.Maintenance.Fix;
import com.grupo2.demo.repository.FixRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fix")
public class RepairController {

    @Autowired
    private RepairRepository fixRepository;

    @PostMapping
    public Repair postMethodName(@RequestBody Repair entity) {
        fixRepository.save(entity);
        return entity;
    }

    @GetMapping
    public List<Repair> getAllFixes() {
        return fixRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repair> getFixById(@PathVariable Long id) {
        Optional<Repair> fix = fixRepository.findById(id);
        return fix.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repair> updateFix(@PathVariable Long id, @RequestBody Repair fixDetails) {
        Optional<Repair> fixOptional = fixRepository.findById(id);

        if (fixOptional.isPresent()) {
            Repair fix = fixOptional.get();
            fix.setData_conserto(fixDetails.getData_conserto());
            fix.setDescricao_conserto(fixDetails.getDescricao_conserto());
            fix.setOrientacao_cliente(fixDetails.getOrientacao_cliente());

            Repair updatedFix = fixRepository.save(fix);
            return ResponseEntity.ok(updatedFix);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFix(@PathVariable Long id) {
        if (fixRepository.existsById(id)) {
            fixRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
