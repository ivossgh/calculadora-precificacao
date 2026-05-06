package com.vendas.principal.model.enums;

public enum Categoria {

    TREINAMENTO("Treinamento"),
    LAUDO_TECNICO("Insalubridade"),
    LAUDO_PGR("PGR"),
    PROGRAMA("PCMSO"),
    EXAME_OCUPACIONAL("Admissional"),
    CONSULTORIA("Visita Técnica");


    private final String descricao;


    Categoria(String descricao) {
        this.descricao = descricao;
    }

   
    public String getDescricao() {
        return descricao;
    }
}