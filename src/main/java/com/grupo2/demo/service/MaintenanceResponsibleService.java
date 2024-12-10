package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.demo.repository.MaintenanceRepository;
import com.grupo2.demo.repository.MaintenanceResponsibleRepository;
import com.grupo2.demo.config.StatusEnum;
import com.grupo2.demo.dto.MaintenanceResponse;
import com.grupo2.demo.dto.RepairRequest;
import com.grupo2.demo.dto.RepairResponse;
import com.grupo2.demo.exception.MaintenanceNotFoundException;
import com.grupo2.demo.model.Maintenance.Maintenance;
import com.grupo2.demo.model.Maintenance.MaintenanceResponsible;
import com.grupo2.demo.model.User.Employee;

@Service
public class MaintenanceResponsibleService {

    @Autowired
    private MaintenanceResponsibleRepository maintenanceResponsibleRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RepairService repairService;

    @Autowired
    private LogResponsibleService logResponsibleService;

    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private MaintenanceRepository maintenanceRepository;;

    @Autowired
    private AuthService authService;

    //Esta sem uso no momento, usar o do RepairService
    public MaintenanceResponse finishFix(Long id, RepairRequest repair) {
        authService.checkEmployeeAuth();
    
        MaintenanceResponsible maintenanceResponsible = maintenanceResponsibleRepository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException("Responsável pela manutenção não encontrado com id: " + id));
    
        maintenanceResponsible.setStatus(true);
        maintenanceResponsibleRepository.save(maintenanceResponsible);
    
        // Buscar a Maintenance associada
        Maintenance maintenance = maintenanceRepository.findByFuncionarioResponsavel(maintenanceResponsible)
                .orElseThrow(() -> new MaintenanceNotFoundException("Manutenção não encontrada para o responsável com id: " + id));
    
        RepairResponse repairResponse = repairService.createRepair(repair);
    
        MaintenanceResponse maintenanceResponse = new MaintenanceResponse();
        maintenanceResponse.setId(maintenance.getId());
        maintenanceResponse.setDescricaoEquipamento(maintenance.getDescricao_equipamento());
        maintenanceResponse.setDescricaoDefeito(maintenance.getDescricao_defeito());
        maintenanceResponse.setNomeCategoria(maintenance.getCategoria().getNome_categoria().toString());
        maintenanceResponse.setDataCriacao(maintenance.getData_criacao());
        maintenanceResponse.setDataFinalizacao(maintenance.getData_finalizacao());
        maintenanceResponse.setNomeCliente(maintenance.getCliente().getNome());
        maintenanceResponse.setNomeStatus(maintenance.getStatus().getNomeStatus());
        maintenanceResponse.setOrientacaoCliente(repairResponse.getOrientacaoCliente());
        maintenanceResponse.setDescricaoConserto(repairResponse.getDescricaoConserto());
        maintenanceResponse.setDataConserto(repairResponse.getDataConserto());
    
        return maintenanceResponse;
    }

    public MaintenanceResponsible redirectResponsible(Long id, Long idFuncionario) {
        authService.checkEmployeeAuth();
        MaintenanceResponsible maintenanceResponsible = maintenanceResponsibleRepository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException("Responsável pela manutenção não encontrado com id: " + id));

        Employee actualEmployee = maintenanceResponsible.getFuncionario();

        Maintenance maintenance = maintenanceRepository.findByFuncionarioResponsavel(maintenanceResponsible)
        .orElseThrow(() -> new MaintenanceNotFoundException("Manutenção não encontrada para o responsável com id: " + id));

        logResponsibleService.createLogResponsible(maintenanceResponsible, actualEmployee);
        maintenanceService.changeStateMaintenance(maintenance.getId(), StatusEnum.REDIRECIONADA);

        Employee employee = employeeService.getEmployeeById2(idFuncionario);
        maintenanceResponsible.setFuncionario(employee);

        return maintenanceResponsibleRepository.save(maintenanceResponsible);
    }

    //-----------Metodos para serem utilizados em outras classes e servicos-----------//
    public void startFirstBudget(Long id, Maintenance maintenance) {

        MaintenanceResponsible maintenanceResponsible = new MaintenanceResponsible();
        Employee employee = employeeService.getEmployeeById2(id);
    
        maintenanceResponsible.setFuncionario(employee);
        maintenanceResponsible.setStatus(false);
    
        maintenanceResponsible = maintenanceResponsibleRepository.save(maintenanceResponsible);
    
        maintenance.setFuncionarioResponsavel(maintenanceResponsible);
        maintenanceRepository.save(maintenance);
    }
    

}
