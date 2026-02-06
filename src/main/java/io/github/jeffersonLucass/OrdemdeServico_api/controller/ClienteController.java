package io.github.jeffersonLucass.OrdemdeServico_api.controller;

import io.github.jeffersonLucass.OrdemdeServico_api.entity.Cliente;
import io.github.jeffersonLucass.OrdemdeServico_api.repository.ClienteRepository;
import io.github.jeffersonLucass.OrdemdeServico_api.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;

    public ClienteController(ClienteRepository clienteRepository,ClienteService clienteService) {
        this.clienteRepository = clienteRepository;
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




}
