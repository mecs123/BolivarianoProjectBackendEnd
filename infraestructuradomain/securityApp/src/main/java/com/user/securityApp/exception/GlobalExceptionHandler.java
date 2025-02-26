package com.user.securityApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Map<String, String>> handleConflict(ConflictException e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("campo", e.getCampo());
        errorResponse.put("mensaje", e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);  // 409 Conflict
    }
    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundEmail(NotFoundUserException e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("campo", e.getCampo());
        errorResponse.put("mensaje", e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);  // 409 Conflict
    }

    // También puedes agregar más manejadores para otras excepciones
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return new ResponseEntity<>("Ha ocurrido un error interno.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
