package com.example.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ClienteRequestDTO;
import com.example.dto.ClienteResponseDTO;
import com.example.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping
	@Operation(summary = "Criar cliente", description = "Cadastra um novo cliente no sistema")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Cliente criado com sucesso", content = @Content(schema = @Schema(implementation = ClienteResponseDTO.class))),
			@ApiResponse(responseCode = "400", description = "Erro de validação ou regra de negócio") })
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteResponseDTO criar(@Valid @RequestBody ClienteRequestDTO dto) {
		return clienteService.criar(dto);
	}

	@GetMapping
	@Operation(summary = "Listar clientes", description = "Lista clientes com paginação, ordenação e filtros opcionais por nome ou email")
	@ApiResponse(responseCode = "200", description = "Lista paginada de clientes")
	public Page<ClienteResponseDTO> listar(@RequestParam(required = false) String nome,
			@RequestParam(required = false) String email,
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

		return clienteService.listarPaginado(nome, email, pageable);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar cliente por ID", description = "Retorna um cliente específico pelo ID")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado") })
	public ClienteResponseDTO buscarPorId(@PathVariable Long id) {
		return clienteService.buscarPorId(id);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente existente")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Erro de validação ou regra de negócio"),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado") })
	public ClienteResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto) {

		return clienteService.atualizar(id, dto);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Excluir cliente", description = "Remove um cliente do sistema")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso"),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		clienteService.deletar(id);
	}
}
