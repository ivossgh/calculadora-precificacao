package com.vendas.principal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "itens_venda")
public class ItemVenda implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

   
    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    
    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;

    @Column(nullable = false)
    private BigDecimal valorUnitario;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal valorTotal;

  
    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public Venda getVenda() {
        return venda;
    }


    public void setVenda(Venda venda) {
        this.venda = venda;
    }


    public Servico getServico() {
        return servico;
    }


    public void setServico(Servico servico) {
        this.servico = servico;
    }


    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }


    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }


    public Integer getQuantidade() {
        return quantidade;
    }


    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


    public BigDecimal getValorTotal() {
        return valorTotal;
    }


    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }


    public void calcularTotal() {
        this.valorTotal = valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    }


}
