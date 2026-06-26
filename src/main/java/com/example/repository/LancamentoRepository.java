package com.example.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.Lancamento;
import com.example.domain.TipoLancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	@Query("SELECT SUM(l.valor) FROM Lancamento l WHERE l.tipo = :tipo")
	BigDecimal somarPorTipo(@Param("tipo") TipoLancamento tipo);

	Page<Lancamento> findByTipo(TipoLancamento tipo, Pageable pageable);
}
