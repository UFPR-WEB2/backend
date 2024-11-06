package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grupo2.demo.model.Maintenance.Fix;
import com.grupo2.demo.repository.FixRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fix")
public class FixController {

    @Autowired
    private FixRepository fixRepository;

    @PostMapping
    public Fix postMethodName(@RequestBody Fix entity) {
        fixRepository.save(entity);
        return entity;
    }

    @GetMapping
    public List<Fix> getAllFixes() {
        return fixRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fix> getFixById(@PathVariable Long id) {
        Optional<Fix> fix = fixRepository.findById(id);
        return fix.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fix> updateFix(@PathVariable Long id, @RequestBody Fix fixDetails) {
        Optional<Fix> fixOptional = fixRepository.findById(id);

        if (fixOptional.isPresent()) {
            Fix fix = fixOptional.get();
            fix.setData_conserto(fixDetails.getData_conserto());
            fix.setDescricao_conserto(fixDetails.getDescricao_conserto());
            fix.setOrientacao_cliente(fixDetails.getOrientacao_cliente());

            Fix updatedFix = fixRepository.save(fix);
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
