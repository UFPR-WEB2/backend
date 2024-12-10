package com.grupo2.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.demo.dto.MaintenanceRequest;
import com.grupo2.demo.dto.MaintenanceResponse;
import com.grupo2.demo.service.MaintenanceService;


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

    @GetMapping("/records")
    public List<MaintenanceResponse> userMaintenances() {
        return maintenanceService.getAllUserMaintenances();
    }

    @GetMapping("/records/{id}")
    public MaintenanceResponse userMaintenancesById(@PathVariable Long id) {
        return maintenanceService.getUserMaintenanceById(id);
    }

    @GetMapping("/emAberto")
    public ResponseEntity<List<MaintenanceResponse>> manutencoesEmAberto() {
        List<MaintenanceResponse> response = maintenanceService.getAllOpenMaintenances();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/finalizadas")
    public ResponseEntity<List<MaintenanceResponse>> manutencoesFinalizadas() {
        List<MaintenanceResponse> response = maintenanceService.getAllFinishedMaintenances();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public MaintenanceResponse atualizarManutencao(@PathVariable Long id,
            @RequestBody MaintenanceRequest maintenanceRequest) {
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

    @PostMapping("/finish")
    public ResponseEntity<MaintenanceResponse> postMethodName(@RequestBody MaintenanceRequest maintenanceRequest) {

        MaintenanceResponse maintenanceResponse = maintenanceService.finishMaintenance(maintenanceRequest.getId());

        return ResponseEntity.ok(maintenanceResponse);
    }

    @PutMapping("/pay/{id}")
    public ResponseEntity<MaintenanceResponse> pagarManutencao(@PathVariable Long id) {
        MaintenanceResponse maintenanceResponse = maintenanceService.payMaintenance(id);
        return ResponseEntity.ok(maintenanceResponse);
    }

}