package com.vendas.principal.model.enums;

import java.math.BigDecimal;

public enum Cliente {
    NOVO_CLIENTE("Novo Cliente", new BigDecimal("-0.08")),
    BASE_CLIENTE("Nosso Cliente", new BigDecimal("-0.3"));

    private final String descricao;
    private final BigDecimal ajustePercentual;

   
    Cliente(String descricao, BigDecimal ajustePercentual) {
        this.descricao = descricao;
        this.ajustePercentual = ajustePercentual;
    }

    public BigDecimal aplicarAjuste(BigDecimal valorBase) {
        BigDecimal taxaFinal = BigDecimal.ONE.add(this.ajustePercentual);
        return valorBase.multiply(taxaFinal);
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getAjustePercentual() {
        return ajustePercentual;
    }
}