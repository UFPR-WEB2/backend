package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo2.demo.dto.MaintenanceRequest;
import com.grupo2.demo.dto.MaintenanceResponse;
import com.grupo2.demo.model.Maintenance.Maintenance;
import com.grupo2.demo.repository.MaintenanceRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    public MaintenanceResponse createMaintenance(MaintenanceRequest maintenanceRequest) {
        Maintenance maintenance = new Maintenance();
        maintenance.setDescricao_equipamento(maintenanceRequest.getDescricaoEquipamento());
        maintenance.setDescricao_defeito(maintenanceRequest.getDescricaoDefeito());
        maintenance.setData_criacao(LocalDate.now());
        maintenance.setData_finalizacao(null); // Inicialmente não finalizada
        Maintenance savedMaintenance = maintenanceRepository.save(maintenance);
        return mapToResponse(savedMaintenance);
    }

    public MaintenanceResponse getMaintenanceById(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manutenção não encontrada com id: " + id));
        return mapToResponse(maintenance);
    }

    public List<MaintenanceResponse> getAllMaintenances() {
        List<Maintenance> maintenances = maintenanceRepository.findAll();
        return maintenances.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public MaintenanceResponse updateMaintenance(Long id, MaintenanceRequest maintenanceRequest) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manutenção não encontrada com id: " + id));
        maintenance.setDescricao_equipamento(maintenanceRequest.getDescricaoEquipamento());
        maintenance.setDescricao_defeito(maintenanceRequest.getDescricaoDefeito());
        maintenance.setData_finalizacao(LocalDate.now());
        Maintenance updatedMaintenance = maintenanceRepository.save(maintenance);
        return mapToResponse(updatedMaintenance);
    }

    public void deleteMaintenance(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manutenção não encontrada com id: " + id));
        maintenanceRepository.delete(maintenance);
    }

    private MaintenanceResponse mapToResponse(Maintenance maintenance) {
        MaintenanceResponse response = new MaintenanceResponse();
        response.setId(maintenance.getId());
        response.setDescricaoEquipamento(maintenance.getDescricao_equipamento());
        response.setDescricaoDefeito(maintenance.getDescricao_defeito());
        response.setDataCriacao(maintenance.getData_criacao());
        response.setDataFinalizacao(maintenance.getData_finalizacao());
        return response;
    }
}