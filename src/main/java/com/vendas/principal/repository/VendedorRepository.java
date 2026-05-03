package com.vendas.principal.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.principal.model.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, UUID>{

}
