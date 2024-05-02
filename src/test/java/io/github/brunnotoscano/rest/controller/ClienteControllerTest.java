package io.github.brunnotoscano.rest.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.brunnotoscano.domain.entity.Cliente;
import io.github.brunnotoscano.domain.repository.Clientes;
import io.github.brunnotoscano.domain.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionConfigurationException;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = ClienteController.class)
class ClienteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClienteService service;

    @Mock
    private Cliente cliente;

    @BeforeEach
    public void init(){
        cliente = new Cliente();
        cliente.setId(1);
        cliente.setTelefone(111L);
        cliente.setNome("Teste");
        cliente.setCorrentista(true);
        cliente.setSaldo_cc(125);
    }
    
    @Test
    public void ClienteController_GetClienteById_ReturnCliente() throws Exception{
        Integer id = 1;
        when(service.getClienteById(id)).thenReturn(cliente);

        mockMvc.perform(get("/api/clientes/{id}", id))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.id").value(cliente.getId()),
                        jsonPath("$.telefone").value(cliente.getTelefone()),
                        jsonPath("$.nome").value(cliente.getNome()),
                        jsonPath("$.correntista").value(cliente.getCorrentista()),
                        jsonPath("$.saldo_cc").value(cliente.getSaldo_cc()),
                        jsonPath("$.score_credito").value(cliente.getScore_credito())

                );

    }

    @Test
    public void ClienteController_GetClienteById_Return404() throws Exception{
        Integer id = 1;
        when(service.getClienteById(id)).thenThrow(new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Cliente não encontrado."));

        mockMvc.perform(get("/api/clientes/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void ClienteController_Save_ReturnCliente() throws Exception{
        when(service.save(cliente)).thenReturn(cliente);
        String body = objectMapper.writeValueAsString(cliente);

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isCreated())
                .andExpectAll(
                        jsonPath("$.id").value(cliente.getId()),
                        jsonPath("$.telefone").value(cliente.getTelefone()),
                        jsonPath("$.nome").value(cliente.getNome()),
                        jsonPath("$.correntista").value(cliente.getCorrentista()),
                        jsonPath("$.saldo_cc").value(cliente.getSaldo_cc()),
                        jsonPath("$.score_credito").value(cliente.getScore_credito())

                );
    }

    @Test
    public void ClienteController_Save_Return400() throws Exception{
        when(service.save(cliente)).thenReturn(cliente);
        cliente.setNome(null);
        cliente.setTelefone(null);
        cliente.setCorrentista(null);
        String body = objectMapper.writeValueAsString(cliente);


        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andDo(print())
                .andExpect(model().attributeHasErrors("nome"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void ClienteController_Delete_Return204() throws Exception {
        Integer id = 1;

        mockMvc.perform(delete("/api/clientes/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void ClienteController_Delete_Return404() throws Exception{

        Integer id = 1;

        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."))
                .when(service).delete(eq(id));

        mockMvc.perform(delete("/api/clientes/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void ClienteController_Update_Return200() throws Exception{
        Integer id = 1;
        String body = objectMapper.writeValueAsString(cliente);


        mockMvc.perform(put("/api/clientes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }

    @Test
    public void ClienteController_Update_Return400() throws Exception{
        Integer id = 1;
        cliente.setNome(null);
        cliente.setTelefone(null);
        cliente.setCorrentista(null);
        String body = objectMapper.writeValueAsString(cliente);

        service.update(1, cliente);

        mockMvc.perform(put("/api/clientes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void ClienteController_Find_ReturnCliente() throws Exception {
        // Cria uma lista de clientes simulada para retornar do serviço
        List<Cliente> clientesSimulados = new ArrayList<>();
        clientesSimulados.add(cliente);
        when(service.find(argThat(clienteBuscado -> clienteBuscado.getNome().equals(cliente.getNome())))).thenReturn(clientesSimulados);


        // Faz uma requisição GET para o endpoint "/api/clientes/buscar" com os parâmetros adequados
        mockMvc.perform(get("/api/clientes/buscar")
                        .param("nome", cliente.getNome()))
                .andExpect(status().isOk())
                        .andExpectAll(
                                jsonPath("$[0].id").value(cliente.getId()),
                                jsonPath("$[0].nome").value(cliente.getNome()),
                                jsonPath("$[0].correntista").value(cliente.getCorrentista())
                        );

    }


}