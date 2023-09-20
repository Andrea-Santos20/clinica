package br.com.clicanicaodontologica.clinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "enderecos")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(length = 100)
    private String logradouro;
    @Column(length = 100)
    private String bairro;
    @Column(length = 100)
    private String cidade;
    @Column(length = 100)
    private String estado;
    @Column(length = 10)
    private String cep;
    @Column(updatable = false)
    private Instant createdAt;
    private Instant updateAt;
    @PrePersist
    public void naCriacao() {
        this.createdAt = Instant.now();
    }
    @PreUpdate
    public void naAtualizacao() {
        this.updateAt = Instant.now();
    }
}
