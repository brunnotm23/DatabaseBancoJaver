package io.github.brunnotoscano.domain.service;

import io.github.brunnotoscano.domain.entity.Cliente;

import java.util.List;

//|****************************************************************************|
//| Descrição: Template dos métodos que as implementações de serviço devem ter |
//|****************************************************************************|

public interface ClienteService {

    Cliente getClienteById(Integer id);
    Cliente save(Cliente cliente);
    void delete(Integer id);
    void update(Integer id, Cliente cliente);
    List<Cliente> find(Cliente filtro);


}
