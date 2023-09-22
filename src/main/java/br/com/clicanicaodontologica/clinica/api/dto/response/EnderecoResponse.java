package br.com.clicanicaodontologica.clinica.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;
@Getter
@Setter
public class EnderecoResponse {
    private UUID id;
    private String logradouro;
    private String bairro;
    private Instant createdAt;
    private Instant updateAt;
    private String cidade;
    private String estado;
    private String cep;
}
