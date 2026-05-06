package com.vendas.principal.controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vendas.principal.model.ItemVenda;
import com.vendas.principal.model.Servico;
import com.vendas.principal.model.Venda;
import com.vendas.principal.repository.ItemVendaRepository;
import com.vendas.principal.repository.ServicoRepository;
import com.vendas.principal.repository.VendaRepository;

@Controller
public class ItemVendaController {

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping("/cadastrar-itemvenda")
    public String formItem(Model model) {
        model.addAttribute("vendas", vendaRepository.findAll());
        model.addAttribute("servicos", servicoRepository.findAll());
        return "createItemVenda";
    }

    @PostMapping("/cadastrar-itemvenda")
    public String salvarItem(
            @RequestParam UUID vendaId,
            @RequestParam UUID servicoId,
            @RequestParam BigDecimal valorUnitario,
            @RequestParam Integer quantidade
    ) {

        Venda venda = vendaRepository.findById(vendaId).orElseThrow();
        Servico servico = servicoRepository.findById(servicoId).orElseThrow();

        ItemVenda item = new ItemVenda();
        item.setVenda(venda);
        item.setServico(servico);
        item.setValorUnitario(valorUnitario);
        item.setQuantidade(quantidade);

        item.calcularTotal();

        // 🔥 PARTE IMPORTANTE (faltava isso)
        venda.getItens().add(item);

        venda.calcularTotalVenda();

        vendaRepository.save(venda); // 🔥 salva tudo via cascade

        return "redirect:/lista-itens";
    }

    @GetMapping("/lista-itens")
    public String listarItens(Model model) {
        model.addAttribute("itens", itemVendaRepository.findAll());
        return "listaItemVenda";
    }
}