package io.vladprotchenko.notificationservice.controller;

import io.vladprotchenko.ensstartercore.exception.EnsServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EnsServiceException.class)
    public ResponseEntity<String> handleEnsServiceException(EnsServiceException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
