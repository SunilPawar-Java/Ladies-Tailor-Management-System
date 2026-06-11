package com.ltr.exception;

import com.ltr.exception.classes.ProductNotFoundException;
import com.ltr.exception.classes.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handelUserNotFoundException(UserNotFoundException userNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("errorMessage", userNotFoundException.getMessage()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("errorMessage", productNotFoundException.getMessage()));
    }
}