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

    @Autowired
    private MaintenanceResponsibleService maintenanceResponsibleService;

    @Autowired
    private AuthService authService;


    public BudgetResponse createBudget(BudgetRequest budgetRequest) {
        Budget budget = new Budget();

        budget.setPrecoOrcado(budgetRequest.getPrecoOrcado());
        budget.setDataOrcamento(LocalDateTime.now());
        budget.setStatus(true);

        Maintenance maintenance = maintenanceRepository.findById(budgetRequest.getMaintenanceId())
                .orElseThrow(() -> new MaintenanceNotFoundException("Manutenção não encontrada com id: " + budgetRequest.getMaintenanceId()));

        budget.setMaintenance(maintenance);

        budgetedMaintenance(budgetRequest.getMaintenanceId());

        //Da para separar isso em uma funcao privada do bugdetService e criar todo uma estrutura para DTO e no MaintenanceResponsibleService somente passar o DTO e um create basico
        maintenanceResponsibleService.startFirstBudget(authService.getEmployee().getId());

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

    //Muda o estado do orcamento para rejeitado
    public void deleteBudget(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException("Orçamento não encontrado com id: " + id));
        budget.setStatus(false);
        budgetRepository.save(budget);
    }

    //Apos ser aprovada muda o estado da manutencao e torna ativo o responsavel criado na primeira
    public BudgetResponse approveBudget(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException("Orçamento não encontrado com id: " + id));

        budget.setDataRecuperacao(LocalDateTime.now());
        budgetRepository.save(budget);

        approveMaintenance(budget.getMaintenance().getId());

        return mapToResponse(budget);
    }

    //Apos ser rejeitado somente muda estado nao mexe em mais nada
    public BudgetResponse rejectBudget(Long id, String justificativaRejeicao) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException("Orçamento não encontrado com id: " + id));

        budget.setJustificativaRejeicao(justificativaRejeicao);
        budget.setDataRejeicao(LocalDateTime.now());
        rejectMaintenance(budget.getMaintenance().getId());

        budgetRepository.save(budget);
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

    //Muda o estado da manutencao que esta associada ao orcamento para rejeitada
    private void rejectMaintenance(Long id) {
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setStatus(StatusEnum.REJEITADA);
        maintenanceService.updateMaintenance(id, maintenanceRequest);
    }

    //Muda o estado da manutencao que esta associada ao orcamento para aprovada
    private void approveMaintenance(Long id) {
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setStatus(StatusEnum.APROVADA);
        maintenanceService.updateMaintenance(id, maintenanceRequest);
    }

    //Muda o estado da manutencao que esta associada ao orcamento para orcada
    private void budgetedMaintenance(Long id) {
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setStatus(StatusEnum.ORCADA);
        maintenanceService.updateMaintenance(id, maintenanceRequest);
    }


}
