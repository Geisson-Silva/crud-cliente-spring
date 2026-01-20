package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
		return new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone(),
				cliente.getAtivo(), cliente.getDataCadastro());
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

	public List<ClienteResponseDTO> listar() {
		return clienteRepository.findAll().stream().map(this::toResponseDTO).toList();
	}

	public ClienteResponseDTO buscarPorId(Long id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));

		return toResponseDTO(cliente);
	}

	public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));

		cliente.setNome(dto.getNome());
		cliente.setEmail(dto.getEmail());
		cliente.setTelefone(dto.getTelefone());

		return toResponseDTO(clienteRepository.save(cliente));
	}

	public void deletar(Long id) {
		if (!clienteRepository.existsById(id)) {
			throw new RegraNegocioException("Cliente não encontrado");
		}

		clienteRepository.deleteById(id);
	}
}
