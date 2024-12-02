package com.grupo2.demo.controller.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.grupo2.demo.exception.BudgetNotFoundException;

@ControllerAdvice
public class BudgetControllerAdvice {

    @ExceptionHandler(BudgetNotFoundException.class)
        public ResponseEntity<Object> handleCategoryNotFound(BudgetNotFoundException ex) {
        Map<String, Object> body = new HashMap<String, Object>();

        body.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
