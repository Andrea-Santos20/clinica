package br.com.clicanicaodontologica.clinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

;

@Getter
@Setter
@Entity
@Table(name = "clinicas")
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id", nullable = false)
    private UUID id;
    private String nome;
    @Column(length = 100, unique = true)
    private String cnpj;
    private String razaoSocial;
    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
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
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    public void naAtualizacao() {
        this.updateAt = LocalDateTime.now();
    }

}
