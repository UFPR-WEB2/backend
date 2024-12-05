package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.demo.dto.MaintenanceResponse;
import com.grupo2.demo.dto.RepairRequest;
import com.grupo2.demo.model.Maintenance.MaintenanceResponsible;
import com.grupo2.demo.service.MaintenanceResponsibleService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/responsavel")
@CrossOrigin(origins = "http://localhost:4200")
public class MaintenanceReponsibleController {

    @Autowired
    private MaintenanceResponsibleService maintenanceResponsibleService;

    @GetMapping("/")
    public String getMethodName(@RequestParam String param) {
        return new String();

    }

    @PostMapping("/conserto")
    public ResponseEntity<MaintenanceResponse> finishFix(@PathVariable Long id, @RequestBody RepairRequest repair) {

        MaintenanceResponse response = maintenanceResponsibleService.finishFix(id, repair);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/{id2}/trocar")
    public ResponseEntity<MaintenanceResponsible> redirectResponsible(@PathVariable Long id, @PathVariable Long id2) {
        MaintenanceResponsible response = maintenanceResponsibleService.redirectResponsible(id, id2);

        return ResponseEntity.ok(response);
    }
    
    
}
