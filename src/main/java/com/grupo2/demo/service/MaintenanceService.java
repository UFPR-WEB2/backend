package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo2.demo.dto.MaintenanceRequest;
import com.grupo2.demo.dto.MaintenanceResponse;
import com.grupo2.demo.exception.MaintenanceNotFoundException;
import com.grupo2.demo.exception.MaintenanceNullException;
import com.grupo2.demo.model.Maintenance.Maintenance;
import com.grupo2.demo.repository.MaintenanceRepository;
import java.util.List;
import com.grupo2.demo.model.Maintenance.Category;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import com.grupo2.demo.repository.StatusRepository;
import com.grupo2.demo.config.StatusEnum;
import com.grupo2.demo.model.Maintenance.Status;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StatusRepository statusRepository;

    public MaintenanceResponse createMaintenance(MaintenanceRequest maintenanceRequest) {

        authService.checkCustomerAuth();

        Maintenance maintenance = new Maintenance();

        if(maintenanceRequest.getDescricaoEquipamento() == null || maintenanceRequest.getDescricaoEquipamento().isEmpty() || maintenanceRequest.getDescricaoDefeito() == null || maintenanceRequest.getDescricaoDefeito().isEmpty()) {
            throw new MaintenanceNullException("Verifique se os campos de descrição do equipamento e defeito estão preenchidos");
        }

        maintenance.setDescricao_equipamento(maintenanceRequest.getDescricaoEquipamento());
        maintenance.setDescricao_defeito(maintenanceRequest.getDescricaoDefeito());
        maintenance.setData_criacao(LocalDateTime.now());
        maintenance.setData_finalizacao(null);

        maintenance.setCliente(authService.getCustomer());
        maintenance.setCategoria(categoryService.obterCategoriaPorNome(maintenanceRequest.getNomeCategoria()));

        Status status = statusRepository.findByNomeStatus(StatusEnum.ABERTA)
                .orElseThrow(() -> new RuntimeException("Status não encontrado"));
        maintenance.setStatus(status);

        Maintenance savedMaintenance = maintenanceRepository.save(maintenance);
        
        return mapToResponse(savedMaintenance);
    }

    public MaintenanceResponse getMaintenanceById(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
            .orElseThrow(() -> new MaintenanceNotFoundException("Manutenção não encontrada com id: " + id));

        return mapToResponse(maintenance);
    }

    public List<MaintenanceResponse> getAllMaintenances() {
        List<Maintenance> maintenances = maintenanceRepository.findAll();
        return maintenances.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public MaintenanceResponse updateMaintenance(Long id, MaintenanceRequest maintenanceRequest) {
        authService.checkCustomerAuth();
    
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException("Manutenção não encontrada com id: " + id));
    
        if (maintenanceRequest.getDescricaoEquipamento() != null && !maintenanceRequest.getDescricaoEquipamento().isEmpty()) {
            maintenance.setDescricao_equipamento(maintenanceRequest.getDescricaoEquipamento());
        }

        if (maintenanceRequest.getDescricaoDefeito() != null && !maintenanceRequest.getDescricaoDefeito().isEmpty()) {
            maintenance.setDescricao_defeito(maintenanceRequest.getDescricaoDefeito());
        }

        if (maintenanceRequest.getNomeCategoria() != null && !maintenanceRequest.getNomeCategoria().isEmpty()) {
            Category category = categoryService.obterCategoriaPorNome(maintenanceRequest.getNomeCategoria());
            maintenance.setCategoria(category);
        }
    
        if (maintenanceRequest.getStatus() != null) {
            Status status = statusRepository.findByNomeStatus(maintenanceRequest.getStatus())
                    .orElseThrow(() -> new RuntimeException("Status não encontrado"));
            maintenance.setStatus(status);
        }
    
        Maintenance updatedMaintenance = maintenanceRepository.save(maintenance);
        return mapToResponse(updatedMaintenance);
    }
    

    public MaintenanceResponse finishMaintenance(Long id) {
        authService.checkAuth();

        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException("Manutenção não encontrada com id: " + id));

        maintenance.setData_finalizacao(LocalDateTime.now());

        Status status = statusRepository.findByNomeStatus(StatusEnum.FINALIZADA)
                .orElseThrow(() -> new RuntimeException("Status não encontrado"));
        maintenance.setStatus(status);

        Maintenance updatedMaintenance = maintenanceRepository.save(maintenance);
        return mapToResponse(updatedMaintenance);
    }


    public void deleteMaintenance(Long id) {
        authService.checkAuth();
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException("Manutenção não encontrada com id: " + id));
        maintenanceRepository.delete(maintenance);
    }

    private MaintenanceResponse mapToResponse(Maintenance maintenance) {
        MaintenanceResponse response = new MaintenanceResponse();
        response.setId(maintenance.getId());
        response.setDescricaoEquipamento(maintenance.getDescricao_equipamento());
        response.setDescricaoDefeito(maintenance.getDescricao_defeito());
        response.setDataCriacao(maintenance.getData_criacao());
        response.setDataFinalizacao(maintenance.getData_finalizacao());

        response.setNomeCategoria(maintenance.getCategoria().getNome_categoria());
        response.setNomeStatus(maintenance.getStatus().getNomeStatus());
        response.setNomeCliente(maintenance.getCliente().getNome());

        return response;
    }

    public void changeStateMaintenance(Long id, StatusEnum status) {
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setStatus(status);
        updateMaintenance(id, maintenanceRequest);
    }

}