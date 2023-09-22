package br.com.clicanicaodontologica.clinica.api.dto.response;

import br.com.clicanicaodontologica.clinica.domain.entity.Clinica;
import br.com.clicanicaodontologica.clinica.domain.entity.EspecialidadeEnum;
import br.com.clicanicaodontologica.clinica.domain.entity.GeneroEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class DentistaResponse {
    private UUID id;
    private String nome;
    private String cro;
    private LocalDate dataNascimento;
    private EspecialidadeEnum especialidade;
    private GeneroEnum genero;
    private Instant createdAt;
    private Instant updateAt;
    private ContatoResponse contato;
    private Set<Clinica> clinicasDentistas;
}
