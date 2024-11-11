package com.IBM.IBM_bank.Repositories;

import com.IBM.IBM_bank.Models.ContaCredito;
import com.IBM.IBM_bank.Models.StatusConta;
import com.IBM.IBM_bank.Models.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContaCreditoRepository extends JpaRepository<ContaCredito, Integer> {

    // Buscar uma conta de crédito por cliente
    //Optional<ContaCredito> findByClienteId(Integer clienteId);

    Optional<ContaCredito> findByContaId(Integer contaId);

    // Buscar todas as contas de crédito com limite superior a um valor específico
    List<ContaCredito> findByLimiteCreditoGreaterThan(Double limiteCredito);

    // Buscar contas de crédito pelo status (ativa, suspensa, etc)
    List<ContaCredito> findByStatus(StatusConta status);

    // Buscar contas de crédito com fatura pendente
    //List<ContaCredito> findByStatusPagamento(StatusPagamento statusPagamento);

    // Buscar contas de crédito de um cliente e tipo de conta específico
    //List<ContaCredito> findByClienteIdAndTipoConta(Integer clienteId, String tipoConta);
}
