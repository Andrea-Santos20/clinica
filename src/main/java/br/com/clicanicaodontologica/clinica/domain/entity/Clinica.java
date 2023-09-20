package br.com.clicanicaodontologica.clinica.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.Instant;
import java.util.UUID;

;

@Getter
@Setter
@Entity
@Table(name = "clinicas")
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private UUID id;
    private String nome;
    @Column(length = 20, unique = true)
    private String cnpj;
    private String razaoSocial;
    @Column(updatable = false)
    private Instant createdAt;
    private Instant updateAt;
    private String descricao;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco" ,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_clinica_endereco"))
    private Endereco endereco;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contato" ,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name="fk_clinica_contato"))
    private Contato contato;

    @PrePersist
    public void naCriacao() {
        this.createdAt = Instant.now();
    }
    @PreUpdate
    public void naAtualizacao() {
        this.updateAt = Instant.now();
    }

}
