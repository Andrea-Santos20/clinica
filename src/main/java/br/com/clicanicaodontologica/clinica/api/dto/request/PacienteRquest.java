package br.com.clicanicaodontologica.clinica.api.dto.request;

import br.com.clicanicaodontologica.clinica.domain.entity.GeneroEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PacienteRquest {
    @NotBlank
    private String nome;
    @NotNull
    private LocalDate dataNascimento;
    @NotNull
    private GeneroEnum genero;
    @NotNull
    private EnderecoRequest endereco;
    @NotNull
    private ContatoRequest contato;
}
