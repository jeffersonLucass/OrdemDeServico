package io.github.jeffersonLucass.OrdemdeServico_api.repository;

import io.github.jeffersonLucass.OrdemdeServico_api.entity.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, UUID> {
}
