package io.github.jeffersonLucass.OrdemdeServico_api.service;

import io.github.jeffersonLucass.OrdemdeServico_api.entity.Cliente;
import io.github.jeffersonLucass.OrdemdeServico_api.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @Transactional
    public Cliente criar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente atualizar(UUID id, Cliente cliente){
        Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cliente Não encontrado")
        );

        clienteExistente.setNome(cliente.getNome());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setTelefone(cliente.getTelefone());

        return clienteRepository.save(clienteExistente);
    }

   public Cliente buscarPorId(UUID id){
        return clienteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado")
        );
   }

   public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
   }

   @Transactional
   public void excluir(UUID id){
        if (!clienteRepository.existsById(id)){
            throw new EntityNotFoundException("Cliente não encontrado");
        }
        clienteRepository.deleteById(id);
   }


}
