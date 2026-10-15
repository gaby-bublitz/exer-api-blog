package com.bublitz.api_blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// CAPTURA A EXCEÇÃO PERSONALIZADA DE RECURSO NÃO ENCONTRADO (POST E COMENTARIO)
	
	@ExceptionHandler(ResourceNotFoundException.class) 
	
	public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
		Map<String, Object> errorBody = new HashMap<>();
		
		errorBody.put("error", "Não Encontrado");
		errorBody.put("message", ex.getMessage()); // MENSAGEM JA EXTRAID DO MESSAGE PROPERTIES pELO MESSAGE SERVICE
		errorBody.put("status", HttpStatus.NOT_FOUND.value());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
	}
	
	
}
