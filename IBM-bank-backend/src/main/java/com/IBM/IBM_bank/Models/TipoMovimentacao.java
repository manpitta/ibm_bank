package com.IBM.IBM_bank.Models;

public enum TipoMovimentacao {
    DEBITO("Débito"),
    CREDITO("Crédito");

    private String descricao;

    TipoMovimentacao(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
