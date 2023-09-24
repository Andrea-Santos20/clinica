package br.com.clicanicaodontologica.clinica.domain.service;

import br.com.clicanicaodontologica.clinica.domain.entity.Clinica;
import br.com.clicanicaodontologica.clinica.domain.exception.NotFoundException;
import lombok.SneakyThrows;

import java.util.List;
import java.util.UUID;

public interface ClinicaService {
    Clinica criarClinica(Clinica clinica);
    List<Clinica> buscarClinicas();

    @SneakyThrows
    Clinica buscarClinicaPorId(UUID id);

    Clinica atualizarClinica(Clinica clinica);

    Clinica atualizarClinica(UUID id, Clinica clinica) throws NotFoundException;
    void deleteClinica(UUID id) throws NotFoundException;
}
