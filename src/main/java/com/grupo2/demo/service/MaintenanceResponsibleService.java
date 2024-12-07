package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private AuthService authService;

    //alem de encerrar cria um conserto para o responsavel
    public MaintenanceResponse finishFix(Long id, RepairRequest repair) {
        authService.checkEmployeeAuth();

        MaintenanceResponsible maintenanceResponsible = maintenanceResponsibleRepository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException("Responsável pela manutenção não encontrado com id: " + id));

        maintenanceResponsible.setStatus(true);

        maintenanceResponsibleRepository.save(maintenanceResponsible);

        RepairResponse repairResponse = repairService.createRepair(maintenanceResponsible, repair);

        MaintenanceResponse maintenanceResponse = new MaintenanceResponse();
        maintenanceResponse.setId(maintenanceResponsible.getManutencao().getId());
        maintenanceResponse.setDescricaoEquipamento(maintenanceResponsible.getManutencao().getDescricao_equipamento());
        maintenanceResponse.setDescricaoDefeito(maintenanceResponsible.getManutencao().getDescricao_defeito());
        maintenanceResponse.setNomeCategoria(maintenanceResponsible.getManutencao().getCategoria().getNome_categoria().toString());
        maintenanceResponse.setDataCriacao(maintenanceResponsible.getManutencao().getData_criacao());
        maintenanceResponse.setDataFinalizacao(maintenanceResponsible.getManutencao().getData_finalizacao());
        maintenanceResponse.setNomeCliente(maintenanceResponsible.getManutencao().getCliente().getNome());
        maintenanceResponse.setNomeStatus(maintenanceResponsible.getManutencao().getStatus().getNomeStatus());
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

        logResponsibleService.createLogResponsible(maintenanceResponsible, actualEmployee);
        maintenanceService.changeStateMaintenance(maintenanceResponsible.getManutencao().getId(), StatusEnum.REDIRECIONADA);

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
        maintenanceResponsible.setManutencao(maintenance);
    
        maintenanceResponsibleRepository.save(maintenanceResponsible);
    }

}
