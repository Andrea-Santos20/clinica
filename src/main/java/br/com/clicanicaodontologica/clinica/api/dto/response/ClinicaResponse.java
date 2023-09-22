package br.com.clicanicaodontologica.clinica.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class ClinicaResponse {
    private UUID id;
    private String nome;
    private String cnpj;
    private String razaoSocial;
    private Instant createdAt;
    private Instant updateAt;
    private String descricao;
    private EnderecoResponse endereco;
    private ContatoResponse contato;
}
