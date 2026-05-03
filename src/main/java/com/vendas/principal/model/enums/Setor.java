package com.vendas.principal.model.enums;

public enum Setor {
    INDUSTRIA("Indústria"),
    CONSTRUCAO("Construção"),
    COMERCIO("Comércio"),
    SERVICOS("Serviços"),
    SAUDE("Saúde"); // Sintaxe corrigida: apenas o valor entre aspas

    private final String descricao;

    // Construtor deve vir após as constantes
    Setor(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}   