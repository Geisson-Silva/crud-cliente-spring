package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	boolean existsByEmail(String email);

	Page<Cliente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

	Page<Cliente> findByEmailContainingIgnoreCase(String email, Pageable pageable);
}
