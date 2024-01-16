package com.example.demo.controller;

import com.example.demo.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleRuntimeException(CustomException ex){
        return ResponseEntity.status(400).body(ex.getMessage());
    }

}
