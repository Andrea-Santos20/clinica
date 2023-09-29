package br.com.clicanicaodontologica.clinica.api.dto.response;

import br.com.clicanicaodontologica.clinica.domain.entity.GeneroEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PacienteResponse {
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private GeneroEnum genero;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private EnderecoResponse endereco;
    private ContatoResponse contato;
}
