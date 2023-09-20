package br.com.clicanicaodontologica.clinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_clinica_endereco"))
    private Endereco endereco;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contato",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_clinica_contato"))
    private Contato contato;
    private GeneroEnum genero;
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