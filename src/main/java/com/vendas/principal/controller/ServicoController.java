package com.vendas.principal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vendas.principal.model.Servico;
import com.vendas.principal.repository.ServicoRepository;



@Controller
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping("/cadastrar-servico")
    public String cadastrarServico(Model model){
        model.addAttribute("servico", new Servico());
        return "createServico";
    }

    @PostMapping("/cadastrar-servico")
    public String salvar(@ModelAttribute Servico servico){
        servicoRepository.save(servico);
        return "redirect:/lista-servicos";
    }

    @GetMapping("/lista-servicos")
    public String listaServicos(Model model){
        model.addAttribute("servicos", servicoRepository.findAll());
        return "listaServicos";
    }

}
