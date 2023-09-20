package br.com.clicanicaodontologica.clinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private UUID id;
    @ManyToOne
    @JoinColumn(name="paciente_id",
            foreignKey = @ForeignKey(
                    name="fk_consulta_paciente"))
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name="dentista_id",
            foreignKey = @ForeignKey(
                    name="fk_consulta_dentista"))
    private Dentista dentista;
    @ManyToOne
    @JoinColumn(name="clinica_id",
            foreignKey = @ForeignKey(
                    name="fk_consulta_clinica"))
    private Clinica clinica;
    private LocalDate dataConsulta;
    @Column(updatable = false)
    private String descricao;
    private Boolean cancelada;
    @Column(length = 80)
    private String motivoCancelamento;
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
