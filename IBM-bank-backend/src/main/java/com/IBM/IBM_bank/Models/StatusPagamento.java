package com.IBM.IBM_bank.Models;

public enum StatusPagamento {
    PAGO("Pago"),
    PENDENTE("Pendente");

    private String descricao;

    StatusPagamento(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
