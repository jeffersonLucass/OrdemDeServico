package io.github.jeffersonLucass.OrdemdeServico_api.controller;

import io.github.jeffersonLucass.OrdemdeServico_api.entity.OrdemServico;
import io.github.jeffersonLucass.OrdemdeServico_api.service.OrdemServicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ordemServico")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping("/cliente/{clienteId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@PathVariable UUID clienteId,@RequestBody @Valid OrdemServico ordemServico) {
        return ordemServicoService.criar(clienteId, ordemServico);
    }

    @PutMapping("/{ordemServicoId}")
    public OrdemServico atualizar(@PathVariable UUID ordemServicoId, @RequestBody @Valid OrdemServico ordemServico) {
        return ordemServicoService.atualizar(ordemServicoId, ordemServico);
    }

    @PatchMapping("/{ordemServicoId}/finalizar")
    public OrdemServico finalizar(@PathVariable UUID ordemServicoId) {
        return ordemServicoService.finalizar(ordemServicoId);
    }


    @GetMapping("/{ordemServicoId}")
    public OrdemServico buscar(@PathVariable UUID ordemServicoId) {
        return ordemServicoService.buscar(ordemServicoId);
    }

    @DeleteMapping("/{ordemServicoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID ordemServicoId) {
        ordemServicoService.deletar(ordemServicoId);
    }




}
