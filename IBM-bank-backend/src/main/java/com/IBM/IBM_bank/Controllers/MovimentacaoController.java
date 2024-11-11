package com.IBM.IBM_bank.Controllers;


import com.IBM.IBM_bank.Models.Movimentacao;
import com.IBM.IBM_bank.Models.StatusPagamento;
import com.IBM.IBM_bank.Models.TipoMovimentacao;
import com.IBM.IBM_bank.Services.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping("/{idConta}/nova")
    public ResponseEntity<Movimentacao> registrarMovimentacao(@PathVariable Integer idConta,
                                                              @RequestBody Movimentacao movimentacao) {
        return ResponseEntity.ok(movimentacaoService.registrarMovimentacao(movimentacao, idConta));
    }

    @GetMapping
    public ResponseEntity<List<Movimentacao>> listarMovimentacoes() {
        return ResponseEntity.ok(movimentacaoService.listarTodasMovimentacoes());
    }

    @GetMapping("/conta/{contaId}")
    public ResponseEntity<List<Movimentacao>> listarMovimentacoesPorConta(
            @PathVariable Integer contaId,
            @RequestParam(required = false) TipoMovimentacao tipo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @RequestParam(required = false) StatusPagamento statusPagamento) {

        List<Movimentacao> movimentacoes = movimentacaoService
                .listarMovimentacoesPorFiltros(contaId, tipo, dataInicio, dataFim, statusPagamento);
        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimentacao> buscarMovimentacao(@PathVariable Integer id) {
        return ResponseEntity.ok(movimentacaoService.buscarMovimentacaoPorId(id));
    }

}
