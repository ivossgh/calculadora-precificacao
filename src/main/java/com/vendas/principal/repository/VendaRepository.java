package com.vendas.principal.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.principal.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, UUID> {

}