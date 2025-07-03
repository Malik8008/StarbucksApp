package com.safarov.starbucks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> getErrors(Exception ex){
        Map<String,String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ExceptionHandler(value = IdNotFoundException.class)
    public ResponseEntity<?> invalidDTO(IdNotFoundException idNotFoundException) {
        return new ResponseEntity<>(idNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
