package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

import com.grupo2.demo.model.Maintenance.Budget;
import com.grupo2.demo.repository.BudgetRepository;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    @GetMapping
    public List<Budget> listarBudgets() {
        return budgetRepository.findAll();
    }

    @PostMapping
    public Budget criarBudget(@RequestBody Budget budget) {
        budget.setDataCriacao(LocalDate.now());
        budget.setDataAtualizacao(LocalDate.now());
        return budgetRepository.save(budget);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> obterBudgetPorId(@PathVariable Long id) {
        Optional<Budget> budget = budgetRepository.findById(id);
        return budget.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Budget> atualizarBudget(@PathVariable Long id, @RequestBody Budget detalhesBudget) {
        Optional<Budget> budgetOptional = budgetRepository.findById(id);

        if (budgetOptional.isPresent()) {
            Budget budget = budgetOptional.get();
            budget.setPrecoOrcado(detalhesBudget.getPrecoOrcado());
            budget.setDescricao(detalhesBudget.getDescricao());
            budget.setDataAtualizacao(LocalDate.now());

            Budget budgetAtualizado = budgetRepository.save(budget);
            return ResponseEntity.ok(budgetAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBudget(@PathVariable Long id) {
        if (budgetRepository.existsById(id)) {
            budgetRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
