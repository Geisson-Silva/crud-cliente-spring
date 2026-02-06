package com.example.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {

	private int status;
	private String mensagem;
	private LocalDateTime timestamp;
	private List<String> erros;

	public ApiError(int status, String mensagem, List<String> erros) {
		this.status = status;
		this.mensagem = mensagem;
		this.erros = erros;
		this.timestamp = LocalDateTime.now();
	}

	public int getStatus() {
		return status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public List<String> getErros() {
		return erros;
	}
}
