package com.IBM.IBM_bank.Services;

import com.IBM.IBM_bank.Models.Conta;
import com.IBM.IBM_bank.Models.ContaCredito;
import com.IBM.IBM_bank.Models.StatusConta;
import com.IBM.IBM_bank.Repositories.ContaCreditoRepository;
import com.IBM.IBM_bank.Services.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaCreditoService {

    @Autowired
    private ContaCreditoRepository contaCreditoRepository;

    @Autowired
    private ContaService contaService;

    public ContaCredito criarContaCredito(ContaCredito contaCredito, Integer idConta) {
        Conta conta = contaService.buscarContaPorId(idConta);
        contaCredito.setConta(conta);
        contaCredito.setSaldoUtilizado(0.0);
        contaCredito.setStatus(StatusConta.ATIVA);
        return contaCreditoRepository.save(contaCredito);
    }

    public ContaCredito buscarContaCreditoPorId(Integer id) {
        Optional<ContaCredito> contaCredito = contaCreditoRepository.findById(id);
        return contaCredito.orElseThrow(
                () -> new EntityNotFoundException("Conta de Crédito não encontrada: " + id));
    }

    public ContaCredito buscarContaCreditoPorConta(Integer idConta) {
        Optional<ContaCredito> contaCredito = contaCreditoRepository.findByContaId(idConta);
        return contaCredito.orElseThrow(
                () -> new EntityNotFoundException("Conta de Crédito não encontrada para a conta: " + idConta));
    }

    public ContaCredito atualizarContaCredito(Integer id, ContaCredito contaCreditoAtualizada) {
        ContaCredito contaCreditoExistente = buscarContaCreditoPorId(id);
        contaCreditoExistente.setLimiteCredito(contaCreditoAtualizada.getLimiteCredito());
        contaCreditoExistente.setDataFechamento(contaCreditoAtualizada.getDataFechamento());
        contaCreditoExistente.setDataPagamento(contaCreditoAtualizada.getDataPagamento());
        return contaCreditoRepository.save(contaCreditoExistente);
    }

    public void deletarContaCredito(Integer id) {
        contaCreditoRepository.deleteById(id);
    }

    public List<ContaCredito> listarTodasContasCredito() {
        return contaCreditoRepository.findAll();
    }
}
