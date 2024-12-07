package com.grupo2.demo.controller.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.grupo2.demo.exception.MaintenanceNotFoundException;
import com.grupo2.demo.exception.MaintenanceNullException;
import com.grupo2.demo.exception.MaintenanceUnprocessableException;

@ControllerAdvice
public class MaintenanceControllerAdvice {

    @ExceptionHandler(MaintenanceNotFoundException.class)
    public ResponseEntity<Object> handleMaintenanceNotFound(MaintenanceNotFoundException ex) {
        Map<String, Object> body = new HashMap<String, Object>();

        body.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MaintenanceNullException.class)
    public ResponseEntity<Object> handleMaintenanceNull(MaintenanceNullException ex) {
        Map<String, Object> body = new HashMap<String, Object>();

        body.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(MaintenanceUnprocessableException.class)
    public ResponseEntity<Object> handleMaintenanceUnprocessable(MaintenanceUnprocessableException ex) {
        Map<String, Object> body = new HashMap<String, Object>();

        body.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(body);
    }
    
}
