package br.com.clicanicaodontologica.clinica.domain.service;

import br.com.clicanicaodontologica.clinica.domain.entity.Clinica;
import br.com.clicanicaodontologica.clinica.domain.entity.Consulta;

import java.util.List;
import java.util.UUID;

public interface ConsultaService {
    Consulta criarConsulta(Consulta consulta);
    List<Consulta> buscarConsultas();
    Consulta buscarConsultaPorId(UUID id);
    Consulta atualizarConsulta(Consulta consulta);
    void deleteConsulta(UUID id);
}