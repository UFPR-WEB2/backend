package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.demo.dto.RepairRequest;
import com.grupo2.demo.dto.RepairResponse;
import com.grupo2.demo.exception.MaintenanceNotFoundException;
import com.grupo2.demo.model.Maintenance.MaintenanceResponsible;
import com.grupo2.demo.model.Maintenance.Repair;
import com.grupo2.demo.repository.RepairRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private AuthService authService;

    public RepairResponse createRepair(MaintenanceResponsible maintenanceResponsible, RepairRequest repairRequest) {
        authService.checkEmployeeAuth();

        Repair repair = new Repair();

        repair.setData_conserto(repairRequest.getDataConserto());
        repair.setDescricao_conserto(repairRequest.getDescricaoConserto());
        repair.setOrientacao_cliente(repairRequest.getOrientacaoCliente());
        repair.setResponsavelManutencao(maintenanceResponsible);

        Repair savedRepair = repairRepository.save(repair);
        return mapToResponse(savedRepair);
    }

    public Repair getRepairById(Long id) {
        return repairRepository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException("Reparo não encontrado com id: " + id));
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

    public Repair getByMaintenanceResponsibleId(Long id) {
        return repairRepository.findByResponsavelManutencaoId(id)
                .orElseThrow(
                        () -> new MaintenanceNotFoundException("Reparo não encontrado com id do responsável: " + id));
    }
}
