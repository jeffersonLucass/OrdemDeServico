package io.github.jeffersonLucass.OrdemdeServico_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "cliente",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
    })
@Getter @Setter
@ToString
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50,nullable = false)
    private String nome;

    @Column(nullable = false,unique = true)
    @Size(max=255)
    @NotBlank(message = "Email não pode estar vazio.")
    @Email(message = "Email não está válido.")
    private String email;

    @Pattern(
        regexp = "^(\\(?\\d{2}\\)?\\s?)?(9?\\d{4})-?\\d{4}$",
        message = "Telefone inválido. Use DDD e número válido"
    )
    @Size(min=10,max=15)
    private String telefone;


    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(nullable = false,updatable = false)
    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

}
