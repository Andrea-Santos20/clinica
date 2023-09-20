package br.com.clicanicaodontologica.clinica.domain.service;

import br.com.clicanicaodontologica.clinica.domain.entity.Clinica;

import java.util.List;
import java.util.UUID;

public interface ClinicaService {
    Clinica criarClinica(Clinica clinica);
    List<Clinica> buscarClinicas();
    Clinica buscarClinicaPorId(UUID id);
    Clinica atualizarClinica(Clinica clinica);

    Clinica atualizarClinica(UUID id, Clinica clinica);

    void deletarClinica(UUID id);
}
