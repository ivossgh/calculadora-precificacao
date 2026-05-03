package com.vendas.principal.model;

import com.vendas.principal.model.enums.Setor;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "empresas")
public class EmpresaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nomeEmpresa;

    @Column(nullable = false, unique = true, length = 18) // Aumentei para 18 para caber pontuação (ex: 00.000.000/0000-00)
    private String cnpj;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private int qtdFuncionario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Setor setorEmpresa; 

    @Column(nullable = false)
    private LocalDate dataCadastro;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Venda> vendas = new ArrayList<>();

    public EmpresaModel() {
    }

    public EmpresaModel(String nomeEmpresa, String cnpj, String telefone, int qtdFuncionario, Setor setorEmpresa, LocalDate dataCadastro) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.qtdFuncionario = qtdFuncionario;
        this.setorEmpresa = setorEmpresa;
        this.dataCadastro = dataCadastro;
    }

    // Getters e Setters
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

    public Setor getSetorEmpresa() { 
        return setorEmpresa; 
    }

    public void setSetorEmpresa(Setor setorEmpresa) { 
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