package com.IBM.IBM_bank.Controllers;

import com.IBM.IBM_bank.Models.Conta;
import com.IBM.IBM_bank.Services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/nova/{idCliente}")
    public ResponseEntity<Conta> criarConta(@PathVariable Integer idCliente,
                                            @RequestBody Conta conta) {
        return ResponseEntity.ok(contaService.criarConta(conta,idCliente));
    }

    @GetMapping
    public ResponseEntity<List<Conta>> listarContas() {
        return ResponseEntity.ok(contaService.listarTodasContas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarConta(@PathVariable Integer id) {
        return ResponseEntity.ok(contaService.buscarContaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> atualizarConta(@PathVariable Integer id, @RequestBody Conta conta) {
        return ResponseEntity.ok(contaService.atualizarConta(id, conta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConta(@PathVariable Integer id) {
        contaService.deletarConta(id);
        return ResponseEntity.noContent().build();
    }

}
