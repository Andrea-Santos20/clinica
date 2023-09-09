package br.com.clicanicaodontologica.clinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
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
    private Instant dataNascimento;
    @Column(length = 80)
    private EspecialidadeEnum especialidade;
    private GeneroEnum sexo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contato" , referencedColumnName = "id")
    private Contato contato;
    @ManyToMany
    @JoinTable(
            name = "clinicaDentista",
            joinColumns = @JoinColumn(name = "id_dentista"),
            inverseJoinColumns = @JoinColumn(name = "id_clinica"),
            foreignKey = @ForeignKey(name = "fk_clinica_dentista")
    )
     private Set<Clinica> clinicasDentistas;
     private Instant createdAt;
     private Instant updateAt;
     @OneToMany(cascade = CascadeType.ALL)
     @JoinColumn(name = "id_dentista")
     private Set<Consulta> consultas;

}
