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
        // Verificar se foi fornecido um ID de conta para movimentações de débito
        Conta conta = contaService.buscarContaPorId(idConta);
        movimentacao.setConta(conta);
        movimentacao.setDataMovimentacao(LocalDate.now());
        if (movimentacao.getTipo() == TipoMovimentacao.DEBITO) {

            // Verificar se a conta está ativa
            if (conta.getStatus() != StatusConta.ATIVA) {
                throw new RuntimeException("A conta fornecida não está ativa.");
            }

            // Verificar se há saldo suficiente para a movimentação de débito
            if (conta.getSaldo() < movimentacao.getValor()) {
                throw new RuntimeException("Saldo insuficiente.");
            }

            // Deduzir o valor do saldo da conta
            conta.setSaldo(conta.getSaldo() - movimentacao.getValor());

            // Atualizar a conta
            contaService.atualizarConta(conta.getId(), conta);

            movimentacao.setStatusPagamento(StatusPagamento.PAGO);
        }
        // Verificar se foi fornecido um ID de conta de crédito para movimentações de crédito
        else if (movimentacao.getTipo() == TipoMovimentacao.CREDITO) {
            ContaCredito contaCredito = contaCreditoService.buscarContaCreditoPorConta(conta.getId());

            // Verificar se a conta de crédito está ativa
            if (contaCredito.getStatus() != StatusConta.ATIVA) {
                throw new RuntimeException("A conta de crédito fornecida não está ativa.");
            }

            // Calcular o limite disponível
            double limiteDisponivel = contaCredito.getLimiteCredito() - contaCredito.getSaldoUtilizado();

            // Verificar se há limite disponível suficiente para a movimentação de débito de crédito
            if (limiteDisponivel < movimentacao.getValor()) {
                throw new RuntimeException("Limite de crédito insuficiente.");
            }

            // Deduzir o valor do saldo utilizado
            contaCredito.setSaldoUtilizado(contaCredito.getSaldoUtilizado() + movimentacao.getValor());

            // O status do pagamento é PENDENTE
            movimentacao.setStatusPagamento(StatusPagamento.PENDENTE);
            // A data da competência é deixada em branco
            movimentacao.setDataCompetencia(null);

            // Atualizar a conta de crédito
            contaCreditoService.atualizarContaCredito(contaCredito.getId(), contaCredito);
        }

        // Salvar a movimentação e retornar
        return movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao> listarMovimentacoesPorFiltros(Integer contaId, TipoMovimentacao tipo, LocalDate dataInicio, LocalDate dataFim, StatusPagamento statusPagamento) {
        // Verifica quais filtros foram fornecidos e faz a busca correspondente
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
            // Caso nenhum filtro adicional seja fornecido, retorna todas as movimentações da conta
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

