package io.github.brunnotoscano.domain.repository;

import io.github.brunnotoscano.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Clientes extends JpaRepository<Cliente, Integer> {
}
