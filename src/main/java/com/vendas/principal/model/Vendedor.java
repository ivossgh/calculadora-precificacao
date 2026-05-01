package com.vendas.principal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="vendedores")
public class Vendedor implements Serializable{
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID  id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(nullable = false)
    private String nomeConsultor;

    @Column(nullable = false)
    private String telefoneConsultor;

    @Column(nullable = false)
    private LocalDate dataContratacao;

    @Column(nullable = false)
    private BigDecimal meta;
    
    @Column(nullable = false)
    private BigDecimal vendasAcumuladasMes = BigDecimal.ZERO;


    public String getNomeConsultor() {
        return nomeConsultor;
    }

    public void setNomeConsultor(String nomeConsultor) {
        this.nomeConsultor = nomeConsultor;
    }

    public String getTelefoneConsultor() {
        return telefoneConsultor;
    }

    public void setTelefoneConsultor(String telefoneConsultor) {
        this.telefoneConsultor = telefoneConsultor;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public BigDecimal getMeta() {
        return meta;
    }

    public void setMeta(BigDecimal meta) {
        this.meta = meta;
    }

    public BigDecimal getVendasAcumuladasMes() {
        return vendasAcumuladasMes;
    }

    public void setVendasAcumuladasMes(BigDecimal vendasAcumuladasMes) {
        this.vendasAcumuladasMes = vendasAcumuladasMes;
    }


}
