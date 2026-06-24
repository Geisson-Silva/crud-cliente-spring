package com.example.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.domain.TipoLancamento;
import com.example.dto.ResumoFinanceiroDTO;
import com.example.repository.LancamentoRepository;

@Service
public class LancamentoService {
	private final LancamentoRepository lancamentoRepository;

	public LancamentoService(LancamentoRepository lancamentoRepository) {
		this.lancamentoRepository = lancamentoRepository;
	}

	public ResumoFinanceiroDTO obterResumoFinanceiro() {
		// Busca os totais do repositório
		BigDecimal receitas = lancamentoRepository.somarPorTipo(TipoLancamento.RECEITA);
		BigDecimal despesas = lancamentoRepository.somarPorTipo(TipoLancamento.DESPESA);

		// Trata valores nulos (caso não existam lançamentos ainda)
		receitas = (receitas == null) ? BigDecimal.ZERO : receitas;
		despesas = (despesas == null) ? BigDecimal.ZERO : despesas;

		BigDecimal saldo = receitas.subtract(despesas);

		return new ResumoFinanceiroDTO(receitas, despesas, saldo);

	}

}
