package com.ltr.exception.handler;

import com.ltr.exception.*;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> userAlreadyExistsExceptionHandler(UserAlreadyExistsException userAlreadyExistsException){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(Map.of("message", userAlreadyExistsException.getMessage()));
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<?> jwtExceptionHandler(JwtException jwtException){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of(
                "message", jwtException.getMessage()
        ));
    }

    @ExceptionHandler(RefreshTokenException.class)
    public ResponseEntity<?> refreshTokenExceptionHandler(RefreshTokenException refreshTokenException){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of(
                "message", refreshTokenException.getMessage()
        ));
    }
}