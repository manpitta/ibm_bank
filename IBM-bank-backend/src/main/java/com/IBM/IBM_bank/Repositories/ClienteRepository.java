package com.IBM.IBM_bank.Repositories;

import com.IBM.IBM_bank.Models.Cliente;
import com.IBM.IBM_bank.Models.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findById(Integer id);

    // Optional<Cliente> findByCpf(String cpf);

    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    //List<Cliente> findByStatus(String status);

    // Buscar cliente por email
    Optional<Cliente> findByEmail(String email);

    //Optional<Cliente> findByTelefone(String telefone);

    //List<Cliente> findByContas_StatusPagamento(StatusPagamento statusPagamento);
}
