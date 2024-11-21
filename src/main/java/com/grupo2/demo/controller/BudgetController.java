package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grupo2.demo.dto.BudgetRequest;
import com.grupo2.demo.dto.BudgetResponse;
import com.grupo2.demo.service.BudgetService;
import java.util.List;

@RestController
@RequestMapping("/api/orcamento")
@CrossOrigin(origins = "http://localhost:4200")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping
    public BudgetResponse criarOrcamento(@RequestBody BudgetRequest budgetRequest) {
        return budgetService.createBudget(budgetRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetResponse> obterOrcamentoPorId(@PathVariable Long id) {
        BudgetResponse budget = budgetService.getBudgetById(id);
        return ResponseEntity.ok(budget);
    }

    @GetMapping
    public List<BudgetResponse> listarOrcamentos() {
        return budgetService.getAllBudgets();
    }

    @PutMapping("/{id}")
    public BudgetResponse atualizarOrcamento(@PathVariable Long id, @RequestBody BudgetRequest budgetRequest) {
        return budgetService.updateBudget(id, budgetRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrcamento(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }
}
