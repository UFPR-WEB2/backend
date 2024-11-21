package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo2.demo.dto.BudgetRequest;
import com.grupo2.demo.dto.BudgetResponse;
import com.grupo2.demo.model.Maintenance.Budget;
import com.grupo2.demo.repository.BudgetRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public BudgetResponse createBudget(BudgetRequest budgetRequest) {
        Budget budget = new Budget();
        budget.setPrecoOrcado(budgetRequest.getPrecoOrcado());
        budget.setDescricao(budgetRequest.getDescricao());
        budget.setDataCriacao(LocalDate.now());
        budget.setDataAtualizacao(LocalDate.now());
        budget.setCategoria(budgetRequest.getCategoria());
        Budget savedBudget = budgetRepository.save(budget);
        return mapToResponse(savedBudget);
    }

    public BudgetResponse getBudgetById(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado com id: " + id));
        return mapToResponse(budget);
    }

    public List<BudgetResponse> getAllBudgets() {
        List<Budget> budgets = budgetRepository.findAll();
        return budgets.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public BudgetResponse updateBudget(Long id, BudgetRequest budgetRequest) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado com id: " + id));
        budget.setPrecoOrcado(budgetRequest.getPrecoOrcado());
        budget.setDescricao(budgetRequest.getDescricao());
        budget.setDataAtualizacao(LocalDate.now());
        Budget updatedBudget = budgetRepository.save(budget);
        return mapToResponse(updatedBudget);
    }

    public void deleteBudget(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado com id: " + id));
        budgetRepository.delete(budget);
    }

    private BudgetResponse mapToResponse(Budget budget) {
        BudgetResponse response = new BudgetResponse();
        response.setId(budget.getId());
        response.setPrecoOrcado(budget.getPrecoOrcado());
        response.setDescricao(budget.getDescricao());
        response.setDataCriacao(budget.getDataCriacao());
        response.setDataAtualizacao(budget.getDataAtualizacao());
        response.setCategoria(budget.getCategoria());
        return response;
    }
}
