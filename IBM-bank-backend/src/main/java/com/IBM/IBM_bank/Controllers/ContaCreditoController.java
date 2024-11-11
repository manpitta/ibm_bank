package com.IBM.IBM_bank.Controllers;

import com.IBM.IBM_bank.Models.ContaCredito;
import com.IBM.IBM_bank.Services.ContaCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contasCredito")
public class ContaCreditoController {

    @Autowired
    private ContaCreditoService contaCreditoService;

    @PostMapping("/nova/{idConta}")
    public ResponseEntity<ContaCredito> criarContaCredito(@PathVariable Integer idConta,
                                                          @RequestBody ContaCredito contaCredito) {
        return ResponseEntity.ok(contaCreditoService.criarContaCredito(contaCredito,idConta));
    }

    @GetMapping
    public ResponseEntity<List<ContaCredito>> listarContasCredito() {
        return ResponseEntity.ok(contaCreditoService.listarTodasContasCredito());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaCredito> buscarContaCredito(@PathVariable Integer id) {
        return ResponseEntity.ok(contaCreditoService.buscarContaCreditoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaCredito> atualizarContaCredito(@PathVariable Integer id, @RequestBody ContaCredito contaCredito) {
        return ResponseEntity.ok(contaCreditoService.atualizarContaCredito(id, contaCredito));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContaCredito(@PathVariable Integer id) {
        contaCreditoService.deletarContaCredito(id);
        return ResponseEntity.noContent().build();
    }

}
