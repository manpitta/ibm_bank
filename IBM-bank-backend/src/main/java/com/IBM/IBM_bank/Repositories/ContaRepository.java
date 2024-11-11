package com.IBM.IBM_bank.Repositories;

import com.IBM.IBM_bank.Models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    // Buscar uma conta pelo ID do cliente
    Optional<Conta> findByClienteId(Integer clienteId);

    Optional<Conta> findByNumero(Integer numero);

    // Buscar todas as contas de um tipo específico
    List<Conta> findByTipoConta(String tipoConta);

    // Buscar todas as contas com saldo maior que um valor específico
    List<Conta> findBySaldoGreaterThan(Double saldo);

    // Buscar todas as contas por status (ativa, inativa, etc)
    List<Conta> findByStatus(String status);

    // Buscar uma conta pelo número da conta (se for um campo único)
    Optional<Conta> findById(Integer numeroConta);
}
