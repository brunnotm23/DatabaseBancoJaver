package io.github.brunnotoscano.rest.controller;


import io.github.brunnotoscano.domain.entity.Cliente;
import io.github.brunnotoscano.domain.service.ClienteService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

//|**************************************************************|
//| Descrição: Controller que cria os endpoints da base de dados |
//|**************************************************************|

@OpenAPIDefinition(info = @Info(title = "Database do Banco Javer",
        description = "Database do Banco Javer que realiza as operações CRUD na base de dados",
        version = "1.0",
        contact = @Contact(name = "Brunno Toscano",
                url = "https://github.com/brunnotm23",
                email = "brunnotm23@gmail.com")
)
)

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Buscar Cliente por ID", description = "Endpoint GET de um Cliente pelo ID fornecido",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso."),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
            }
    )
    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable Integer id){
        return clienteService.getClienteById(id);
    }


    @Operation(summary = "Criar Cliente", description = "Cria um cliente com as informações fornecidas",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Informações fornecidas são inválidas.")
            }
    )
    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente save (@RequestBody @Valid Cliente cliente){
        return clienteService.save(cliente);
    }


    @Operation(summary = "Deletar Cliente", description = "Deleta o cliente que possuir o ID fornecido",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Operação realizada."),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
            }
    )
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id){
        clienteService.delete(id);
    }


    @Operation(summary = "Atualizar Cliente", description = "Atualiza um cliente com as informações fornecidas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso."),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado."),
                    @ApiResponse(responseCode = "400", description = "Informações inválidas.")
            }
    )
    @PutMapping("{id}")
    public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente){
        clienteService.update(id,cliente);
    }


    @Operation(summary = "Buscar Cliente", description = "Busca clientes baseado nos parâmetros fornecidos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso.")
            }
    )
    @GetMapping("/buscar")
    public List<Cliente> find(Cliente filtro){
        return clienteService.find(filtro);
    }

}
