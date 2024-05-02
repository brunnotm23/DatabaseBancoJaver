package io.github.brunnotoscano.rest.controller;


import io.github.brunnotoscano.domain.entity.Cliente;
import io.github.brunnotoscano.domain.repository.Clientes;
import io.github.brunnotoscano.domain.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;


import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable Integer id){
        return clienteService.getClienteById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente save (@RequestBody @Valid Cliente cliente){
        return clienteService.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id){
        clienteService.delete(id);
    }


    @PutMapping("{id}")
    public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente){
        clienteService.update(id,cliente);
    }

    @GetMapping("/buscar")
    public List<Cliente> find(Cliente filtro){
        return clienteService.find(filtro);
    }

}
