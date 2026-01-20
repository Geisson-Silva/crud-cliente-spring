package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ClienteRequestDTO;
import com.example.dto.ClienteResponseDTO;
import com.example.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping
	public ClienteResponseDTO criar(@Valid @RequestBody ClienteRequestDTO dto) {
		return clienteService.criar(dto);
	}

	@GetMapping
	public List<ClienteResponseDTO> listar() {
		return clienteService.listar();
	}

	@GetMapping("/{id}")
	public ClienteResponseDTO buscarPorId(@PathVariable Long id) {
		return clienteService.buscarPorId(id);
	}

	@PutMapping("/{id}")
	public ClienteResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto) {
		return clienteService.atualizar(id, dto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		clienteService.deletar(id);
	}
}
