package com.IBM.IBM_bank.Models;

public enum StatusConta {
    ATIVA("Ativa"),
    INATIVA("Pendente");

    private String descricao;

    StatusConta(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
