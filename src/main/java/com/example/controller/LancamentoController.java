package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ResumoFinanceiroDTO;
import com.example.service.LancamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

	private final LancamentoService lancamentoService;

	public LancamentoController(LancamentoService lancamentoService) {
		this.lancamentoService = lancamentoService;
	}

	@GetMapping("/relatorios/saldo")
	@Operation(summary = "Buscar Resumo Financeiro", description = "Retorna o saldo total de receitas e despesas")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Saldo encontrado"),
			@ApiResponse(responseCode = "404", description = "Saldo não encontrado") })
	public ResumoFinanceiroDTO buscarSaldo() {
		return lancamentoService.obterResumoFinanceiro();
	}
}
