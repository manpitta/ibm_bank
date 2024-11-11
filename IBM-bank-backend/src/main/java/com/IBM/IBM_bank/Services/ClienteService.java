package com.IBM.IBM_bank.Services;

import com.IBM.IBM_bank.Models.Cliente;
import com.IBM.IBM_bank.Models.Conta;
import com.IBM.IBM_bank.Models.StatusConta;
import com.IBM.IBM_bank.Models.TipoConta;
import com.IBM.IBM_bank.Repositories.ClienteRepository;
import com.IBM.IBM_bank.Repositories.ContaRepository;
import com.IBM.IBM_bank.Services.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Transactional
    public Cliente criarClienteComConta(Cliente cliente, Integer numeroConta) {
        Cliente clienteSalvo = clienteRepository.save(cliente);

        Conta novaConta = new Conta();
        novaConta.setNumero(numeroConta);
        novaConta.setTipoConta(TipoConta.CORRENTE);
        novaConta.setSaldo(0.0);
        novaConta.setStatus(StatusConta.ATIVA);
        novaConta.setCliente(clienteSalvo);

        Conta conta = contaRepository.save(novaConta);

        clienteSalvo.setConta(conta);
        return clienteSalvo;
    }

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente buscarClientePorId(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(
                () -> new EntityNotFoundException("Cliente não cadastrado: " + id));
    }

    public Cliente atualizarCliente(Integer id, Cliente clienteAtualizado) {
        Cliente clienteExistente = buscarClientePorId(id);
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setIdade(clienteAtualizado.getIdade());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        return clienteRepository.save(clienteExistente);
    }

    public void deletarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }
}
