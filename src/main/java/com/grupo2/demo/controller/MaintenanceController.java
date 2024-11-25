package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grupo2.demo.dto.MaintenanceRequest;
import com.grupo2.demo.dto.MaintenanceResponse;
import com.grupo2.demo.service.MaintenanceService;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/manutencao")
@CrossOrigin(origins = "http://localhost:4200")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @PostMapping
    public ResponseEntity<MaintenanceResponse> criarManutencao(@RequestBody MaintenanceRequest maintenanceRequest) {
        MaintenanceResponse maintenance = maintenanceService.createMaintenance(maintenanceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenance);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceResponse> obterManutencaoPorId(@PathVariable Long id) {
        MaintenanceResponse maintenance = maintenanceService.getMaintenanceById(id);
        return ResponseEntity.ok(maintenance);
    }

    @GetMapping
    public List<MaintenanceResponse> listarManutencoes() {
        return maintenanceService.getAllMaintenances();
    }

    @PutMapping("/{id}")
    public MaintenanceResponse atualizarManutencao(@PathVariable Long id, @RequestBody MaintenanceRequest maintenanceRequest) {
        return maintenanceService.updateMaintenance(id, maintenanceRequest);
    }

    @PutMapping("finish/{id}")
    public ResponseEntity<MaintenanceResponse> putMethodName(@PathVariable Long id) {
        
        MaintenanceResponse entity = maintenanceService.finishMaintenance(id);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarManutencao(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}