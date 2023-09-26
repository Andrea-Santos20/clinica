package br.com.clicanicaodontologica.clinica.api.dto.response;

import br.com.clicanicaodontologica.clinica.domain.entity.Clinica;
import br.com.clicanicaodontologica.clinica.domain.entity.Dentista;
import br.com.clicanicaodontologica.clinica.domain.entity.Paciente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ConsultaResponse {
    private UUID id;
    private Paciente paciente;
    private Dentista dentista;
    private Clinica clinica;
    private LocalDate dataConsulta;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private String descricao;
    private Boolean cancelada;
    private String motivoCancelamento;
}
