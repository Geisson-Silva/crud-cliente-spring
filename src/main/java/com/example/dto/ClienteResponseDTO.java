package com.example.dto;

import java.time.LocalDateTime;

import com.example.domain.Cliente;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de retorno do cliente")
public class ClienteResponseDTO {

	@Schema(example = "1")
	private Long id;

	private String nome;
	private String email;
	private String telefone;
	private Boolean ativo;

	@Schema(example = "2026-02-06T14:47:38")
	private LocalDateTime dataCadastro;

	public ClienteResponseDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		this.ativo = cliente.getAtivo();
		this.dataCadastro = cliente.getDataCadastro();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
}
