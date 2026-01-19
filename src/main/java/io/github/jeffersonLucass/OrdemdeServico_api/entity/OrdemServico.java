package io.github.jeffersonLucass.OrdemdeServico_api.entity;
//SOLICITACAO DE SERVICO FEITA POR UM CLIENTE

import io.github.jeffersonLucass.OrdemdeServico_api.Enums.StatusOrdemServico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "OrdemServico",schema = "public")
@Getter @Setter
@ToString
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Cliente é obrigatório")
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOrdemServico status;

    private BigDecimal valor;

    @Column(nullable = false,updatable = false)
    private LocalDateTime dataAbertura;

    private LocalDateTime dataConclusao;

    //OS sempre é criada Aberta

    @PrePersist
    protected void onCreate() {
        this.status = StatusOrdemServico.ABERTA;
        this.dataAbertura = LocalDateTime.now();
    }



}
