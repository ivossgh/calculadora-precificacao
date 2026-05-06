package com.vendas.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.vendas.principal.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, UUID> {

}
