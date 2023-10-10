package com.habarbanua.controller;

import com.habarbanua.model.Response;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Response<String>> violationsException(ConstraintViolationException violationException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Response.<String>builder().errors(violationException.getMessage()).build());
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Response<String>> responseStatusException(ResponseStatusException exception){
        return ResponseEntity.status(exception.getStatusCode())
                .body(Response.<String>builder().errors(exception.getReason()).build());
    }
}
