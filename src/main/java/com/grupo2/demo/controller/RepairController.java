package com.grupo2.demo.controller;

import com.grupo2.demo.dto.RepairRequest;
import com.grupo2.demo.dto.RepairResponse;
import com.grupo2.demo.service.RepairService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repair")
@CrossOrigin(origins = "http://localhost:4200")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @PostMapping
    public RepairResponse createRepair(@RequestBody RepairRequest repairRequest) {
        return repairService.createRepair(repairRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairResponse> getRepairById(@PathVariable Long id) {
        RepairResponse repair = repairService.getRepairById(id);
        return ResponseEntity.ok(repair);
    }

    @GetMapping
    public List<RepairResponse> getAllRepairs() {
        return repairService.getAllRepairs();
    }

    @PutMapping("/{id}")
    public RepairResponse updateRepair(@PathVariable Long id, @RequestBody RepairRequest repairRequest) {
        return repairService.updateRepair(id, repairRequest);
    }

    // Ajustar no BD para evitar perda da integridade
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepair(@PathVariable Long id) {
        repairService.deleteRepair(id);
        return ResponseEntity.noContent().build();
    }
}
