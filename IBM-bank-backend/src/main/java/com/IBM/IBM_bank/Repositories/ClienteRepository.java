package com.IBM.IBM_bank.Repositories;

import com.IBM.IBM_bank.Models.Cliente;
import com.IBM.IBM_bank.Models.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Buscar cliente por ID
    Optional<Cliente> findById(Integer id);

    // Buscar cliente por CPF (ou outro campo único)
    // Optional<Cliente> findByCpf(String cpf);

    // Buscar cliente pelo nome
    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    // Buscar todos os clientes com um determinado status (ativo, inativo, etc)
    //List<Cliente> findByStatus(String status);

    // Buscar cliente por email
    Optional<Cliente> findByEmail(String email);

    // Buscar cliente por telefone (se for necessário)
    //Optional<Cliente> findByTelefone(String telefone);

    // Buscar todos os clientes com contas associadas a um determinado status de pagamento
    //List<Cliente> findByContas_StatusPagamento(StatusPagamento statusPagamento);
}
