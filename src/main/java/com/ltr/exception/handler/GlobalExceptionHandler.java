package com.ltr.exception.handler;

import com.ltr.exception.OrderNotFoundException;
import com.ltr.exception.ProductNotFoundException;
import com.ltr.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundExceptionHandler(UserNotFoundException userNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", userNotFoundException.getLocalizedMessage()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> productNotFoundExceptionHandler(ProductNotFoundException productNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", productNotFoundException.getLocalizedMessage()));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<?> orderNotFoundExceptionHandler(OrderNotFoundException orderNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", orderNotFoundException.getLocalizedMessage()));
    }
}