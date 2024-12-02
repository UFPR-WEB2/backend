package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.demo.config.StatusEnum;
import com.grupo2.demo.dto.BudgetRequest;
import com.grupo2.demo.dto.BudgetResponse;
import com.grupo2.demo.dto.MaintenanceRequest;
import com.grupo2.demo.exception.BudgetNotFoundException;
import com.grupo2.demo.exception.MaintenanceNotFoundException;
import com.grupo2.demo.model.Maintenance.Budget;
import com.grupo2.demo.model.Maintenance.Maintenance;
import com.grupo2.demo.repository.BudgetRepository;
import com.grupo2.demo.repository.MaintenanceRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private MaintenanceService maintenanceService;

    public BudgetResponse createBudget(BudgetRequest budgetRequest) {
        Budget budget = new Budget();

        budget.setPrecoOrcado(budgetRequest.getPrecoOrcado());
        budget.setDataOrcamento(LocalDateTime.now());
        budget.setStatus(true);

        Maintenance maintenance = maintenanceRepository.findById(budgetRequest.getMaintenanceId())
                .orElseThrow(() -> new MaintenanceNotFoundException("Manutenção não encontrada com id: " + budgetRequest.getMaintenanceId()));
        budget.setMaintenance(maintenance);

        Budget savedBudget = budgetRepository.save(budget);
        return mapToResponse(savedBudget);
    }

    public BudgetResponse getBudgetById(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException("Orçamento não encontrado com id: " + id));
        return mapToResponse(budget);
    }

    public List<BudgetResponse> getAllBudgets() {
        List<Budget> budgets = budgetRepository.findAll();
        return budgets.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public BudgetResponse updateBudget(Long id, BudgetRequest budgetRequest) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException("Orçamento não encontrado com id: " + id));

        if (budgetRequest.getPrecoOrcado() != null) {
            budget.setPrecoOrcado(budgetRequest.getPrecoOrcado());
        }

        Budget updatedBudget = budgetRepository.save(budget);
        return mapToResponse(updatedBudget);
    }

    public void deleteBudget(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException("Orçamento não encontrado com id: " + id));
        budget.setStatus(false);
        budgetRepository.save(budget);
    }

    public BudgetResponse approveBudget(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException("Orçamento não encontrado com id: " + id));

        budget.setDataRecuperacao(LocalDateTime.now());
        budgetRepository.save(budget);
        approveMaintenance(budget.getMaintenance().getId());
        
        return mapToResponse(budget);
    }

    public BudgetResponse rejectBudget(Long id, String justificativaRejeicao) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException("Orçamento não encontrado com id: " + id));

        budget.setJustificativaRejeicao(justificativaRejeicao);
        budget.setDataRejeicao(LocalDateTime.now());
        budgetRepository.save(budget);
        rejectMaintenance(budget.getMaintenance().getId());
        return mapToResponse(budget);
    }

    private BudgetResponse mapToResponse(Budget budget) {
        BudgetResponse response = new BudgetResponse();

        response.setId(budget.getId());
        response.setPrecoOrcado(budget.getPrecoOrcado());
        response.setDataOrcamento(budget.getDataOrcamento());
        response.setStatus(budget.getStatus());
        response.setJustificativaRejeicao(budget.getJustificativaRejeicao());
        response.setDataRejeicao(budget.getDataRejeicao());
        response.setDataRecuperacao(budget.getDataRecuperacao());

        return response;
    }

    private void rejectMaintenance(Long id) {
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setStatus(StatusEnum.REJEITADA);
        maintenanceService.updateMaintenance(id, maintenanceRequest);
    }

    private void approveMaintenance(Long id) {
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setStatus(StatusEnum.APROVADA);
        maintenanceService.updateMaintenance(id, maintenanceRequest);
    }
}
