package com.IBM.IBM_bank.Repositories;

import com.IBM.IBM_bank.Models.Movimentacao;
import com.IBM.IBM_bank.Models.StatusPagamento;
import com.IBM.IBM_bank.Models.TipoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {

    List<Movimentacao> findByContaId(Integer contaId);

    List<Movimentacao> findByContaIdAndTipo(Integer contaId, TipoMovimentacao tipo);

    List<Movimentacao> findByContaIdAndDataMovimentacaoBetween(Integer contaId, LocalDate dataInicio, LocalDate dataFim);

    List<Movimentacao> findByContaIdAndStatusPagamento(Integer contaId, StatusPagamento statusPagamento);

    List<Movimentacao> findByContaIdAndTipoAndDataMovimentacaoBetween(Integer contaId, TipoMovimentacao tipo, LocalDate dataInicio, LocalDate dataFim);

    List<Movimentacao> findByContaIdAndDataMovimentacaoBetweenAndStatusPagamento(Integer contaId, LocalDate dataInicio, LocalDate dataFim, StatusPagamento statusPagamento);

    List<Movimentacao> findByContaIdAndTipoAndDataMovimentacaoBetweenAndStatusPagamento(Integer contaId, TipoMovimentacao tipo, LocalDate dataInicio, LocalDate dataFim, StatusPagamento statusPagamento);

    List<Movimentacao> findByContaIdAndTipoAndStatusPagamento(Integer contaId, TipoMovimentacao tipo, StatusPagamento statusPagamento);

    Optional<Movimentacao> findById(Integer id);
}
