package com.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para criação ou atualização de cliente")
public class ClienteRequestDTO {

	@Schema(description = "Nome do cliente", example = "João da Silva")
	@NotBlank
	@Size(min = 3, max = 100)
	private String nome;

	@Schema(description = "Email do cliente", example = "joao@email.com")
	@NotBlank
	@Email
	private String email;

	@Schema(description = "Telefone do cliente", example = "+55 11 91234-5678")
	@NotBlank
	private String telefone;

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}
}
