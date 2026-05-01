package com.vendas.principal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendas")
public class Venda implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private EmpresaModel empresa;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVenda> itens = new ArrayList<>();

    @Column(nullable = false)
    private LocalDate dataVenda;

    @Column(nullable = false)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal desconto = BigDecimal.ZERO;

    public UUID getId() {
        return id; 
    }
    public void setId(UUID id) { 
        this.id = id; 
    }

    public EmpresaModel getEmpresa() { 
        return empresa; 
    }

    public void setEmpresa(EmpresaModel empresa) { 
        this.empresa = empresa; 
    }

    public Vendedor getVendedor() { 
        return vendedor; 
    }

    public void setVendedor(Vendedor vendedor) { 
        this.vendedor = vendedor; 
    }

    public List<ItemVenda> getItens() { 
        return itens; 
    }

    public void setItens(List<ItemVenda> itens) { 
        this.itens = itens; 
    }

    public LocalDate getDataVenda() { 
        return dataVenda; 
    }

    public void setDataVenda(LocalDate dataVenda) { 
        this.dataVenda = dataVenda; 
    }

    public BigDecimal getValorTotal() { 
        return valorTotal; 
    }

    public void setValorTotal(BigDecimal valorTotal) { 
        this.valorTotal = valorTotal; 
    }

    public BigDecimal getDesconto() { 
        return desconto; 
    }
    
    public void setDesconto(BigDecimal desconto) { 
        this.desconto = desconto; 
    }
}