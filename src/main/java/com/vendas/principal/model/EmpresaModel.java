package com.vendas.principal.model;

import java.io.Serializable;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresas")
public class EmpresaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nomeEmpresa;

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private int qtdFuncionario;

    @Column(nullable = false)
    private String setorEmpresa; 

    @Column(nullable = false)
    private LocalDate dataCadastro;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Venda> vendas = new ArrayList<>();

    public UUID getId() { 
        return id; 
    }

    public void setId(UUID id) { 
        this.id = id; 
    }

    public String getNomeEmpresa() { 
        return nomeEmpresa; 
    }

    public void setNomeEmpresa(String nomeEmpresa) { 
        this.nomeEmpresa = nomeEmpresa; 
    }

    public String getCnpj() { 
        return cnpj; 
    }

    public void setCnpj(String cnpj) { 
        this.cnpj = cnpj; 
    }

    public String getTelefone() { 
        return telefone; 
    }

    public void setTelefone(String telefone) { 
        this.telefone = telefone; 
    }

    public int getQtdFuncionario() { 
        return qtdFuncionario; 
    }

    public void setQtdFuncionario(int qtdFuncionario) { 
        this.qtdFuncionario = qtdFuncionario; 
    }

    public String getSetorEmpresa() { 
        return setorEmpresa; 
    }
    public void setSetorEmpresa(String setorEmpresa) { 
        this.setorEmpresa = setorEmpresa; 
    }

    public LocalDate getDataCadastro() { 
        return dataCadastro; 
    }

    public void setDataCadastro(LocalDate dataCadastro) { 
        this.dataCadastro = dataCadastro; 
    }

    public List<Venda> getVendas() { 
        return vendas; 
    }
    public void setVendas(List<Venda> vendas) { 
        this.vendas = vendas; 
    }
}