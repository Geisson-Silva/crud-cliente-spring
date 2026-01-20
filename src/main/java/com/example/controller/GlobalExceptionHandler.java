package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.exception.RegraNegocioException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RegraNegocioException.class)
	public ResponseEntity<String> tratarRegraNegocio(RegraNegocioException ex) {
	    return ResponseEntity.badRequest().body(ex.getMessage());
	}

}
