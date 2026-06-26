package com.example.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Lancamento;
import com.example.domain.TipoLancamento;
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

	@GetMapping
	@Operation(summary = "Listar Lançamentos", description = "Retorna uma lista paginada de lançamentos, podendo filtrar por tipo")
	public Page<Lancamento> listar(@RequestParam(required = false) TipoLancamento tipo,
			@ParameterObject @PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable) {
		if (tipo != null) {
			return lancamentoService.listarPorTipo(tipo, pageable);

		}

		return lancamentoService.listarTodos(pageable);
	}

}
