package com.grupo2.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.demo.config.StatusEnum;
import com.grupo2.demo.dto.RepairRequest;
import com.grupo2.demo.dto.RepairResponse;
import com.grupo2.demo.exception.MaintenanceNotFoundException;
import com.grupo2.demo.model.Maintenance.Maintenance;
import com.grupo2.demo.model.Maintenance.MaintenanceResponsible;
import com.grupo2.demo.model.Maintenance.Repair;
import com.grupo2.demo.model.Maintenance.Status;
import com.grupo2.demo.repository.MaintenanceRepository;
import com.grupo2.demo.repository.MaintenanceResponsibleRepository;
import com.grupo2.demo.repository.RepairRepository;
import com.grupo2.demo.repository.StatusRepository;

@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private MaintenanceResponsibleRepository maintenanceResponsibleRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private StatusRepository statusRepository;

    public RepairResponse createRepair(RepairRequest repairRequest) {
        authService.checkEmployeeAuth();

        Repair repair = new Repair();

        repair.setDescricao_conserto(repairRequest.getDescricaoConserto());
        repair.setOrientacao_cliente(repairRequest.getOrientacaoCliente());
        repair.setData_conserto(LocalDateTime.now());

        Long idMaintenance = repairRequest.getIdManutencao();
        MaintenanceResponsible maintenanceResponsible = maintenanceRepository.findById(idMaintenance)
                .orElseThrow(() -> new MaintenanceNotFoundException("Manutenção não encontrada com id: " + idMaintenance))
                .getFuncionarioResponsavel();

        Repair savedRepair = repairRepository.save(repair);
        maintenanceResponsible.setReparo(repair);
        maintenanceResponsibleRepository.save(maintenanceResponsible);

        Maintenance maintenance = maintenanceRepository.findById(idMaintenance)
                .orElseThrow(() -> new MaintenanceNotFoundException("Manutenção não encontrada com id: " + idMaintenance));

        Status status = statusRepository.findByNomeStatus(StatusEnum.ARRUMADA)
                .orElseThrow(() -> new MaintenanceNotFoundException("Status não encontrado"));
    
        maintenance.setStatus(status);
        maintenanceRepository.save(maintenance);

        return mapToResponse(savedRepair);
    }

    public RepairResponse getRepairById(Long id) {
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException("Reparo não encontrado com id: " + id));
        return mapToResponse(repair);
    }

    public List<RepairResponse> getAllRepairs() {
        List<Repair> repairs = repairRepository.findAll();
        return repairs.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public RepairResponse updateRepair(Long id, RepairRequest repairRequest) {
        authService.checkEmployeeAuth();

        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException("Reparo não encontrado com id: " + id));
            
        repair.setData_conserto(repairRequest.getDataConserto());
        repair.setDescricao_conserto(repairRequest.getDescricaoConserto());
        repair.setOrientacao_cliente(repairRequest.getOrientacaoCliente());

        Repair updatedRepair = repairRepository.save(repair);
        return mapToResponse(updatedRepair);
    }

    public void deleteRepair(Long id) {
        authService.checkEmployeeAuth();
        if (!repairRepository.existsById(id)) {
            throw new MaintenanceNotFoundException("Reparo não encontrado com id: " + id);
        }
        repairRepository.deleteById(id);
    }

    private RepairResponse mapToResponse(Repair repair) {
        RepairResponse response = new RepairResponse();
        response.setId(repair.getId());
        response.setDataConserto(repair.getData_conserto());
        response.setDescricaoConserto(repair.getDescricao_conserto());
        response.setOrientacaoCliente(repair.getOrientacao_cliente());
        return response;
    }
}
