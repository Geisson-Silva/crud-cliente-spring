package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	 boolean existsByEmail(String email);
}
