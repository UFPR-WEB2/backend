package com.grupo2.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.demo.model.Maintenance.LogResponsible;
import com.grupo2.demo.model.Maintenance.MaintenanceResponsible;
import com.grupo2.demo.model.User.Employee;
import com.grupo2.demo.repository.LogResponsibleRepository;

@Service
public class LogResponsibleService {

    @Autowired
    private LogResponsibleRepository logResponsibleRepository;

    public LogResponsible createLogResponsible(MaintenanceResponsible maintenanceResponsible, Employee employee) {
        LogResponsible logResponsible = new LogResponsible();

        logResponsible.setFuncionario(employee);
        logResponsible.setManutencaoResponsavel(maintenanceResponsible);
        logResponsible.setData_redirecionamento(LocalDateTime.now());

        return logResponsibleRepository.save(logResponsible);
    }

    public LogResponsible lastResponsible(MaintenanceResponsible maintenanceResponsible) {
        return logResponsibleRepository.findTopByManutencaoResponsavelOrderByDataRedirecionamentoDesc(maintenanceResponsible);
    }
}
