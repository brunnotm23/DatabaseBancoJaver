package io.github.brunnotoscano.domain.service.impl;

import io.github.brunnotoscano.domain.entity.Cliente;
import io.github.brunnotoscano.domain.repository.Clientes;
import io.github.brunnotoscano.domain.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

//|**********************************************************************************|
//| Descrição: Serviço que contém os métodos e operações utilizadas na base de dados |
//|**********************************************************************************|

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final Clientes repository;

    @Override
    public Cliente getClienteById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado."));
    }

    @Override
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public void delete(Integer id) {
        repository.findById(id)
                .map(cliente -> { repository.delete(cliente); return Void.class; })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    @Override
    public void update(Integer id, Cliente cliente) {
        repository.findById(id).map( clienteExistente -> {
            cliente.setId(clienteExistente.getId());
            repository.save(cliente);
            return clienteExistente;
        }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado."));
    }

    @Override
    public List<Cliente> find(Cliente filtro) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnorePaths("saldo_cc")
                .withIgnorePaths("score_credito");
        Example example = Example.of(filtro, exampleMatcher);
        return repository.findAll(example);
    }
}
