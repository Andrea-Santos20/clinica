package br.com.clicanicaodontologica.clinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "dentistas")
public class Dentista {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    private String nome;
    private String cro;
    private LocalDate dataNascimento;
    @Column(length = 80)
    private EspecialidadeEnum especialidade;
    private GeneroEnum genero;
    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contato" , referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_dentista_contato"))
    private Contato contato;
    @ManyToMany
    @JoinTable(
            name = "clinicaDentista",
            joinColumns = @JoinColumn(name = "id_dentista", foreignKey = @ForeignKey(name="fk_dentista_clinica")),
            inverseJoinColumns = @JoinColumn(name = "id_clinica", foreignKey = @ForeignKey(name="fk_clinica_dentista"))
    )

     private Set<Clinica> clinicaDentista;
    @PrePersist
    public void naCriacao() {
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    public void naAtualizacao() {
        this.updateAt = LocalDateTime.now();
    }
}
