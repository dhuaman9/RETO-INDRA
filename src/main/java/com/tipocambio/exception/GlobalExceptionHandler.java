package com.tipocambio.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	 
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleGenericException(Exception ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado.");
	    }
	    

	    @ExceptionHandler(ResponseStatusException.class)
	    public ResponseEntity<Map<String, String>> handleNotFoundException(ResponseStatusException ex) {
	        return ResponseEntity.status(ex.getStatusCode()).body(Collections.singletonMap("error", ex.getReason()));
	    }
	
	
}
