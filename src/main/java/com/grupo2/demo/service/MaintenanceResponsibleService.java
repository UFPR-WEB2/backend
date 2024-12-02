package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.demo.repository.MaintenanceReposibleRepository;
import com.grupo2.demo.dto.RepairRequest;
import com.grupo2.demo.model.Maintenance.MaintenanceResponsible;
import com.grupo2.demo.model.User.Employee;

@Service
public class MaintenanceResponsibleService {

    @Autowired
    private MaintenanceReposibleRepository maintenanceResponsibleRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RepairService repairService;

    public void startFirstBudget(Long id) {

        MaintenanceResponsible maintenanceResponsible = new MaintenanceResponsible();
        
        Employee employee = employeeService.getEmployeeById2(id);

        maintenanceResponsible.setFuncionario(employee);
        maintenanceResponsible.setStatus(false);
    
        maintenanceResponsibleRepository.save(maintenanceResponsible);
    }


    //alem de encerrar cria um conserto para o responsavel
    public void finishBudget(Long id, RepairRequest repair) {
        MaintenanceResponsible maintenanceResponsible = maintenanceResponsibleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Responsável pela manutenção não encontrado com id: " + id));

        maintenanceResponsible.setStatus(true);

        maintenanceResponsibleRepository.save(maintenanceResponsible);
        repairService.createRepair(maintenanceResponsible, repair);
    }


    public void changeResponsible() {
        //Criar Funcao para mudar responsavel
    }
}
