package br.com.clicanicaodontologica.clinica.api.dto.response.wrapperResponse;

import br.com.clicanicaodontologica.clinica.api.dto.response.listResponse.PacienteListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PacienteWrapperResponse {
    List<PacienteListResponse> pacientes;
}
