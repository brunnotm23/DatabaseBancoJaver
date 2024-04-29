package io.github.brunnotoscano.domain.service;

import io.github.brunnotoscano.domain.entity.Cliente;
import io.github.brunnotoscano.domain.repository.Clientes;

import java.util.List;

public interface ClienteService {

    Cliente getClienteById(Integer id);
    Cliente save(Cliente cliente);
    void delete(Integer id);
    void update(Integer id, Cliente cliente);
    List<Cliente> find(Cliente filtro);


}
