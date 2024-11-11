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

    //Optional<ContaCredito> findByClienteId(Integer clienteId);

    Optional<ContaCredito> findByContaId(Integer contaId);

    List<ContaCredito> findByLimiteCreditoGreaterThan(Double limiteCredito);

    List<ContaCredito> findByStatus(StatusConta status);

    //List<ContaCredito> findByStatusPagamento(StatusPagamento statusPagamento);

    //List<ContaCredito> findByClienteIdAndTipoConta(Integer clienteId, String tipoConta);
}
