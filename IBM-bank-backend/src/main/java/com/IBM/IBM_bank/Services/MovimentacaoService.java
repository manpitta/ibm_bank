package com.IBM.IBM_bank.Services;

import com.IBM.IBM_bank.Models.*;
import com.IBM.IBM_bank.Repositories.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ContaService contaService;

    @Autowired
    private ContaCreditoService contaCreditoService;

    public Movimentacao registrarMovimentacao(Movimentacao movimentacao, Integer idConta) {
        Conta conta = contaService.buscarContaPorId(idConta);
        movimentacao.setConta(conta);
        movimentacao.setDataMovimentacao(LocalDate.now());
        if (movimentacao.getTipo() == TipoMovimentacao.DEBITO) {

            if (conta.getStatus() != StatusConta.ATIVA) {
                throw new RuntimeException("A conta fornecida não está ativa.");
            }

            if (conta.getSaldo() < movimentacao.getValor()) {
                throw new RuntimeException("Saldo insuficiente.");
            }

            conta.setSaldo(conta.getSaldo() - movimentacao.getValor());

            contaService.atualizarConta(conta.getId(), conta);

            movimentacao.setStatusPagamento(StatusPagamento.PAGO);
        }

        else if (movimentacao.getTipo() == TipoMovimentacao.CREDITO) {
            ContaCredito contaCredito = contaCreditoService.buscarContaCreditoPorConta(conta.getId());

            if (contaCredito.getStatus() != StatusConta.ATIVA) {
                throw new RuntimeException("A conta de crédito fornecida não está ativa.");
            }

            double limiteDisponivel = contaCredito.getLimiteCredito() - contaCredito.getSaldoUtilizado();

            if (limiteDisponivel < movimentacao.getValor()) {
                throw new RuntimeException("Limite de crédito insuficiente.");
            }

            contaCredito.setSaldoUtilizado(contaCredito.getSaldoUtilizado() + movimentacao.getValor());

            movimentacao.setStatusPagamento(StatusPagamento.PENDENTE);
            movimentacao.setDataCompetencia(null);

            contaCreditoService.atualizarContaCredito(contaCredito.getId(), contaCredito);
        }

        return movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao> listarMovimentacoesPorFiltros(Integer contaId, TipoMovimentacao tipo, LocalDate dataInicio, LocalDate dataFim, StatusPagamento statusPagamento) {

        if (tipo != null && dataInicio != null && dataFim != null && statusPagamento != null) {
            return movimentacaoRepository.findByContaIdAndTipoAndDataMovimentacaoBetweenAndStatusPagamento(contaId, tipo, dataInicio, dataFim, statusPagamento);
        } else if (tipo != null && dataInicio != null && dataFim != null) {
            return movimentacaoRepository.findByContaIdAndTipoAndDataMovimentacaoBetween(contaId, tipo, dataInicio, dataFim);
        } else if (dataInicio != null && dataFim != null && statusPagamento != null) {
            return movimentacaoRepository.findByContaIdAndDataMovimentacaoBetweenAndStatusPagamento(contaId, dataInicio, dataFim, statusPagamento);
        } else if (tipo != null && statusPagamento != null) {
            return movimentacaoRepository.findByContaIdAndTipoAndStatusPagamento(contaId, tipo, statusPagamento);
        } else if (dataInicio != null && dataFim != null) {
            return movimentacaoRepository.findByContaIdAndDataMovimentacaoBetween(contaId, dataInicio, dataFim);
        } else if (tipo != null) {
            return movimentacaoRepository.findByContaIdAndTipo(contaId, tipo);
        } else if (statusPagamento != null) {
            return movimentacaoRepository.findByContaIdAndStatusPagamento(contaId, statusPagamento);
        } else {

            return movimentacaoRepository.findByContaId(contaId);
        }
    }

    public Movimentacao buscarMovimentacaoPorId(Integer id) {
        Optional<Movimentacao> movimentacao = movimentacaoRepository.findById(id);
        return movimentacao.orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));
    }

    public List<Movimentacao> listarTodasMovimentacoes() {
        return movimentacaoRepository.findAll();
    }
}

