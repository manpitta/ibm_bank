package com.IBM.IBM_bank.Services;

import com.IBM.IBM_bank.Models.Cliente;
import com.IBM.IBM_bank.Models.Conta;
import com.IBM.IBM_bank.Repositories.ClienteRepository;
import com.IBM.IBM_bank.Repositories.ContaRepository;
import com.IBM.IBM_bank.Services.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteService clienteService;

    public Conta criarConta(Conta conta, Integer idCliente) {
        Cliente cliente = clienteService.buscarClientePorId(idCliente);
        conta.setCliente(cliente);
        return contaRepository.save(conta);
    }

    public Conta buscarContaPorId(Integer id) {
        Optional<Conta> conta = contaRepository.findById(id);
        return conta.orElseThrow(
                () -> new EntityNotFoundException("Conta não cadastrada: " + id));
    }

    public Conta atualizarConta(Integer id, Conta contaAtualizada) {
        Conta contaExistente = buscarContaPorId(id);
        contaExistente.setTipoConta(contaAtualizada.getTipoConta());
        contaExistente.setSaldo(contaAtualizada.getSaldo());
        contaExistente.setStatus(contaAtualizada.getStatus());
        return contaRepository.save(contaExistente);
    }

    public void deletarConta(Integer id) {
        contaRepository.deleteById(id);
    }

    public List<Conta> listarTodasContas() {
        return contaRepository.findAll();
    }
}
