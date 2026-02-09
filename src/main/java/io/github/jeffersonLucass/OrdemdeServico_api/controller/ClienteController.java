package io.github.jeffersonLucass.OrdemdeServico_api.controller;

import io.github.jeffersonLucass.OrdemdeServico_api.entity.Cliente;
import io.github.jeffersonLucass.OrdemdeServico_api.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public Cliente criarCliente(@RequestBody Cliente cliente){
        System.out.println("Cliente criado com sucesso " + cliente);

        return clienteService.criar(cliente);
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable UUID id,@RequestBody Cliente cliente) {

        System.out.println("Cliente atualizado com sucesso " + cliente);
        return clienteService.atualizar(id, cliente);
    }

    @GetMapping
    public List<Cliente> listarClientes(){
        System.out.println("Cliente listado com sucesso");

        return clienteService.listarTodos();

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable  UUID id){
        clienteService.excluir(id);
    }



}
