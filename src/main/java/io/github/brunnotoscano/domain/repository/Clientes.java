package io.github.brunnotoscano.domain.repository;

import io.github.brunnotoscano.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

//|*********************************************************************************|
//| Descrição: Repositório responsável pelas operações de Clientes na base de dados |
//|*********************************************************************************|

public interface Clientes extends JpaRepository<Cliente, Integer> {
}
