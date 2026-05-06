package com.vendas.principal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vendas.principal.model.ItemVenda;
import com.vendas.principal.model.Servico;
import com.vendas.principal.model.Venda;
import com.vendas.principal.model.enums.Cliente;
import com.vendas.principal.repository.EmpresaRepository;
import com.vendas.principal.repository.ServicoRepository;
import com.vendas.principal.repository.VendaRepository;
import com.vendas.principal.repository.VendedorRepository;

@Controller
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping("/cadastrar-venda")
    public String formVenda(Model model) {
        model.addAttribute("empresas", empresaRepository.findAll());
        model.addAttribute("vendedores", vendedorRepository.findAll());
        model.addAttribute("clientes", Cliente.values());
        model.addAttribute("servicos", servicoRepository.findAll());
        return "createVenda";
    }

    @PostMapping("/cadastrar-venda")
    public String salvarVenda(
            Venda venda, // O Spring preenche automaticamente os campos simples e a lista de itens
            @RequestParam UUID empresaId,
            @RequestParam UUID vendedorId
    ) {

        // 1. Vincular os objetos principais
        venda.setEmpresa(empresaRepository.findById(empresaId).orElseThrow());
        venda.setVendedor(vendedorRepository.findById(vendedorId).orElseThrow());

        // 2. Processar a lista de itens (limpar vazios e vincular o serviço real)
        List<ItemVenda> itensValidos = new ArrayList<>();
        
        for (ItemVenda item : venda.getItens()) {
            // Verifica se o item tem um serviço selecionado
            if (item.getServico() != null && item.getServico().getId() != null) {
                Servico servico = servicoRepository.findById(item.getServico().getId()).orElseThrow();
                
                item.setServico(servico);
                item.setVenda(venda); // Garante a ligação bidirecional
                itensValidos.add(item);
            }
        }
        
        venda.setItens(itensValidos);

        // 3. Salvar (O @PrePersist calcularTotalVenda() será disparado automaticamente)
        vendaRepository.save(venda);

        return "redirect:/lista-vendas";
    }

    @GetMapping("/lista-vendas")
    public String listar(Model model) {
        model.addAttribute("vendas", vendaRepository.findAll());
        return "listaVendas";
    }
}