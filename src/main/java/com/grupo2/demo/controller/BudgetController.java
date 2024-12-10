package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo2.demo.dto.BudgetRejectRequest;
import com.grupo2.demo.dto.BudgetRequest;
import com.grupo2.demo.dto.BudgetResponse;
import com.grupo2.demo.dto.MaintenanceResponse;
import com.grupo2.demo.service.BudgetService;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/orcamentos")
@CrossOrigin(origins = "http://localhost:4200")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping
    public ResponseEntity<MaintenanceResponse> criarOrcamento(@RequestBody BudgetRequest budgetRequest) {
        MaintenanceResponse budgetResponse = budgetService.createBudget(budgetRequest);
        return ResponseEntity.ok(budgetResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetResponse> obterOrcamentoPorId(@PathVariable Long id) {
        BudgetResponse budget = budgetService.getBudgetById(id);
        return ResponseEntity.ok(budget);
    }

    @GetMapping
    public ResponseEntity<List<BudgetResponse>> listarOrcamentos() {
        List<BudgetResponse> budgets = budgetService.getAllBudgets();
        return ResponseEntity.ok(budgets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetResponse> atualizarOrcamento(@PathVariable Long id, @RequestBody BudgetRequest budgetRequest) {
        BudgetResponse budgetResponse = budgetService.updateBudget(id, budgetRequest);
        return ResponseEntity.ok(budgetResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrcamento(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/aprovar/{id}")
    public ResponseEntity<BudgetResponse> aprovarOrcamento(@PathVariable Long id) {
        BudgetResponse budgetResponse = budgetService.approveBudget(id);
        return ResponseEntity.ok(budgetResponse);
    }

    @PutMapping("/rejeitar/{id}")
    public ResponseEntity<BudgetResponse> rejeitarOrcamento(@PathVariable Long id, @RequestBody BudgetRejectRequest rejectRequest) {
        BudgetResponse budgetResponse = budgetService.rejectBudget(id, rejectRequest.getJustificativaRejeicao());
        return ResponseEntity.ok(budgetResponse);
    }

    @PutMapping("/resgatar/{id}")
    public ResponseEntity<BudgetResponse> resgatarOrcamento(@PathVariable Long id) {
        BudgetResponse budgetResponse = budgetService.redeemBudget(id);
        return ResponseEntity.ok(budgetResponse);
    }

    @GetMapping("/manutencao/{id}")
    public ResponseEntity<BudgetResponse> obterOrcamentoPorManutencao(@PathVariable Long id) {
        BudgetResponse budget = budgetService.getBudgetByMaintenanceId(id);
        return ResponseEntity.ok(budget);
    }
}
