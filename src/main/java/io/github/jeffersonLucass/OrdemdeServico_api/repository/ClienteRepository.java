package io.github.jeffersonLucass.OrdemdeServico_api.repository;

import io.github.jeffersonLucass.OrdemdeServico_api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

}
