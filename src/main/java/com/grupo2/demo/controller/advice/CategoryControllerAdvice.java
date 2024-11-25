package com.grupo2.demo.controller.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.grupo2.demo.exception.CategoryConflitException;
import com.grupo2.demo.exception.CategoryNotFoundException;
import com.grupo2.demo.exception.CategoryNullException;

@ControllerAdvice
public class CategoryControllerAdvice {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFound(CategoryNotFoundException ex) {
        Map<String, Object> body = new HashMap<String, Object>();

        body.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(CategoryNullException.class)
    public ResponseEntity<Object> handleCategoryNull(CategoryNullException ex) {
        Map<String, Object> body = new HashMap<String, Object>();

        body.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(CategoryConflitException.class)
    public ResponseEntity<Object> handleCategoryEx(CategoryConflitException ex) {
        Map<String, Object> body = new HashMap<String, Object>();

        body.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

}
