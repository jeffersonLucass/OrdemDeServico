package io.github.jeffersonLucass.OrdemdeServico_api.service;

import io.github.jeffersonLucass.OrdemdeServico_api.Enums.StatusOrdemServico;
import io.github.jeffersonLucass.OrdemdeServico_api.entity.Cliente;
import io.github.jeffersonLucass.OrdemdeServico_api.entity.OrdemServico;
import io.github.jeffersonLucass.OrdemdeServico_api.repository.ClienteRepository;
import io.github.jeffersonLucass.OrdemdeServico_api.repository.OrdemServicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;
    private final ClienteRepository clienteRepository;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository, ClienteRepository clienteRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.clienteRepository = clienteRepository;
    }

    // 🔹 Criar OS
    @Transactional
    public OrdemServico criar(UUID clienteId, OrdemServico ordemServico) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }


    // 🔹 Atualizar OS (somente se não estiver FINALIZADA)
    @Transactional
    public OrdemServico atualizar(UUID ordemServicoId, OrdemServico ordemServico) {

        OrdemServico osExists = ordemServicoRepository.findById(ordemServicoId).orElseThrow(
                (EntityNotFoundException::new)
        );
        if(osExists.getStatus() == StatusOrdemServico.FINALIZADA){
            throw  new RuntimeException("Ordem de serviço finalizada não pode ser alterada");
        }

        osExists.setDescricao(ordemServico.getDescricao());
        osExists.setValor(ordemServico.getValor());
        osExists.setStatus(ordemServico.getStatus());


        return ordemServicoRepository.save(osExists);
    }

    // FINALIZAR OS

    @Transactional
    public OrdemServico finalizar(UUID ordemServicoId){

        OrdemServico osExists = ordemServicoRepository.findById(ordemServicoId).orElseThrow(
                (EntityNotFoundException::new)
        );

        if (osExists.getStatus() == StatusOrdemServico.FINALIZADA){
            throw new RuntimeException("Ordem de serviço já está finalizada");
        }

        osExists.setStatus(StatusOrdemServico.FINALIZADA);
        osExists.setDataConclusao(LocalDateTime.now());

        return ordemServicoRepository.save(osExists);

    }



    // 🔹 Buscar por ID
    public  OrdemServico buscar(UUID ordemServicoId){
        return ordemServicoRepository.findById(ordemServicoId).orElseThrow(
                () -> new EntityNotFoundException("Ordem de serviço não encontrada")
        );
    }



    // 🔹 Deletar OS
    @Transactional
    public void deletar(UUID ordemServicoId){

        OrdemServico os  =  ordemServicoRepository.findById(ordemServicoId).orElseThrow(
                (RuntimeException::new)
        );

        if(os.getStatus() == StatusOrdemServico.FINALIZADA){
            throw new RuntimeException("Não é possível deletar uma OS FINALIZADA");
        }
        ordemServicoRepository.delete(os);

    }



}
