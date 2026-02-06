package com.example.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {

		List<String> erros = ex.getBindingResult().getFieldErrors().stream()
				.map(e -> e.getField() + ": " + e.getDefaultMessage()).collect(Collectors.toList());

		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", erros);

		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(RegraNegocioException.class)
	public ResponseEntity<ApiError> handleRegraNegocio(RegraNegocioException ex) {

		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), List.of(ex.getMessage()));

		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ApiError> handleResponseStatus(ResponseStatusException ex) {

		ApiError error = new ApiError(ex.getStatusCode().value(), ex.getReason(), List.of(ex.getReason()));

		return ResponseEntity.status(ex.getStatusCode()).body(error);
	}
}
