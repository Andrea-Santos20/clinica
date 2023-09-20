package br.com.clicanicaodontologica.clinica.api.dto.request;

import br.com.clicanicaodontologica.clinica.domain.entity.Clinica;
import br.com.clicanicaodontologica.clinica.domain.entity.EspecialidadeEnum;
import br.com.clicanicaodontologica.clinica.domain.entity.GeneroEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class DentistaRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String cro;
    @NotNull
    private LocalDate dataNascimento;
    @NotNull
    private EspecialidadeEnum especialidade;
    @NotNull
    private GeneroEnum genero;
    @NotNull
    private ContatoRequest contato;
    @NotEmpty
    private Set<Clinica> clinicasDentistas;

}
