package com.vendas.principal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import com.vendas.principal.model.Vendedor;
import com.vendas.principal.repository.VendedorRepository;

@Controller
public class VendedorController {

    @Autowired
    private VendedorRepository vendedorRepository;

    @GetMapping("/cadastrar-consultor")
    public String cadastrarConsultor(Model model){
        model.addAttribute("vendedor", new Vendedor());
        return "createVendedor";
    }
    
    @PostMapping("/cadastrar-consultor")
    public String salvar(@ModelAttribute Vendedor vendedor){
        vendedorRepository.save(vendedor);
        return "redirect:/lista-consultores";
    }

    @GetMapping("/lista-consultores")
    public String listarConsultor(Model model){
        // consultores é que vou enviar para meu listaConsulores, onde vou percorrer
        model.addAttribute("consultores", vendedorRepository.findAll());
        return "listaConsultores";
    }

}
