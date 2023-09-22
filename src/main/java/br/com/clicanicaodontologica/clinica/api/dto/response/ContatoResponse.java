package br.com.clicanicaodontologica.clinica.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;
@Getter
@Setter
public class ContatoResponse {
    private UUID id;
    private String email;
    private String telefone;
    private Instant createdAt;
    private Instant updateAt;
}
