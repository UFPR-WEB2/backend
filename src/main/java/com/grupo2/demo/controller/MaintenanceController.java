package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grupo2.demo.dto.MaintenanceRequest;
import com.grupo2.demo.dto.MaintenanceResponse;
import com.grupo2.demo.service.MaintenanceService;
import java.util.List;

@RestController
@RequestMapping("/api/manutencao")
@CrossOrigin(origins = "http://localhost:4200")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @PostMapping
    public MaintenanceResponse criarManutencao(@RequestBody MaintenanceRequest maintenanceRequest) {
        return maintenanceService.createMaintenance(maintenanceRequest);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarManutencao(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}