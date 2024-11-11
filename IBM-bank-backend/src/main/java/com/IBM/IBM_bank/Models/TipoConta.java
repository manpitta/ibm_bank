package com.IBM.IBM_bank.Models;

public enum TipoConta {

    CORRENTE("Corrente"),
    POUPANCA("Poupança");

    private String descricao;

    TipoConta(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
