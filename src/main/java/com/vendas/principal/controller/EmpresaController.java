package com.vendas.principal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vendas.principal.model.EmpresaModel;
import com.vendas.principal.repository.EmpresaRepository;

@Controller
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping("/cadastrar-empresa")
    public String cadastrarEmpresa(Model model){
        model.addAttribute("empresa", new EmpresaModel());
        return "createEmpresa";
    }

    @PostMapping("/cadastrar-empresa")
    public String salvar(@ModelAttribute EmpresaModel empresa){
        String cnpjLimpo = empresa.getCnpj().replaceAll("[^0-9]", "");
        empresa.setCnpj(cnpjLimpo);
        empresaRepository.save(empresa);
        
        return "redirect:/lista-empresas";
    }

    @GetMapping("/lista-empresas")
    public String listarEmpresas(Model model){
        model.addAttribute("empresas", empresaRepository.findAll());
        return "listaEmpresas";
    }
}
