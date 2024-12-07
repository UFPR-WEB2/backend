package com.grupo2.demo.controller.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.grupo2.demo.exception.EmployeeNullException;
import com.grupo2.demo.exception.EmployeeSelfDeleteException;

@ControllerAdvice
public class EmployeeControllerAdvice {
    @ExceptionHandler(EmployeeNullException.class)
    public ResponseEntity<Object> handleEmployeeNull(EmployeeNullException ex) {
        Map<String, Object> body = new HashMap<String, Object>();

        body.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(EmployeeSelfDeleteException.class)
    public ResponseEntity<Object> handleEmployeeSelfDelete(EmployeeSelfDeleteException ex) {
        Map<String, Object> body = new HashMap<String, Object>();
        
        body.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }
}
