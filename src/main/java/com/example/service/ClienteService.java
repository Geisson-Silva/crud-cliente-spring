package com.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.domain.Cliente;
import com.example.dto.ClienteRequestDTO;
import com.example.dto.ClienteResponseDTO;
import com.example.exception.RegraNegocioException;
import com.example.repository.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	private ClienteResponseDTO toResponseDTO(Cliente cliente) {
		return new ClienteResponseDTO(cliente);
	}

	public ClienteResponseDTO criar(ClienteRequestDTO dto) {

		if (clienteRepository.existsByEmail(dto.getEmail())) {
			throw new RegraNegocioException("Email já cadastrado");
		}

		Cliente cliente = new Cliente();
		cliente.setNome(dto.getNome());
		cliente.setEmail(dto.getEmail());
		cliente.setTelefone(dto.getTelefone());

		return toResponseDTO(clienteRepository.save(cliente));
	}

	public Page<ClienteResponseDTO> listarPaginado(String nome, String email, Pageable pageable) {

		Page<Cliente> page;

		if (nome != null && !nome.isBlank()) {
			page = clienteRepository.findByNomeContainingIgnoreCase(nome, pageable);
		} else if (email != null && !email.isBlank()) {
			page = clienteRepository.findByEmailContainingIgnoreCase(email, pageable);
		} else {
			page = clienteRepository.findAll(pageable);
		}

		return page.map(ClienteResponseDTO::new);
	}

	public ClienteResponseDTO buscarPorId(Long id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

		return toResponseDTO(cliente);
	}

	public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {

		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

		if (clienteRepository.existsByEmail(dto.getEmail()) && !cliente.getEmail().equals(dto.getEmail())) {
			throw new RegraNegocioException("Email já cadastrado");
		}

		cliente.setNome(dto.getNome());
		cliente.setEmail(dto.getEmail());
		cliente.setTelefone(dto.getTelefone());

		return toResponseDTO(clienteRepository.save(cliente));
	}

	public void deletar(Long id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

		clienteRepository.delete(cliente);
	}

}
