package com.IBM.IBM_bank.Repositories;

import com.IBM.IBM_bank.Models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    Optional<Conta> findByClienteId(Integer clienteId);

    Optional<Conta> findByNumero(Integer numero);

    List<Conta> findByTipoConta(String tipoConta);

    List<Conta> findBySaldoGreaterThan(Double saldo);

    List<Conta> findByStatus(String status);

    Optional<Conta> findById(Integer numeroConta);
}
