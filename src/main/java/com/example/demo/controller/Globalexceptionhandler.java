package com.example.demo.controller;

import com.example.demo.dto.Apiresponsedto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class Globalexceptionhandler {

    // Tangani validation error dari @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Apiresponsedto<Map<String, String>>> handleValidationErrors(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Apiresponsedto<>(false, "Validasi gagal", errors));
    }

    // Tangani runtime exception umum
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Apiresponsedto<Void>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Apiresponsedto.error(ex.getMessage()));
    }

    // Tangani exception umum lainnya
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Apiresponsedto<Void>> handleException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Apiresponsedto.error("Terjadi kesalahan: " + ex.getMessage()));
    }
}
