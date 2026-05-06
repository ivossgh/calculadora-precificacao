package com.vendas.principal.model.enums;

public enum Setor {

    INDUSTRIA("Indústria"),
    CONSTRUCAO("Construção"),
    COMERCIO("Comércio"),
    SERVICOS("Serviços");
    

    private final String descricao;

    Setor(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}