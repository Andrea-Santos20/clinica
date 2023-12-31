package br.com.clicanicaodontologica.clinica.domain.service;

import br.com.clicanicaodontologica.clinica.domain.entity.Paciente;

import java.util.List;
import java.util.UUID;

public interface PacienteService {
    Paciente criarPaciente(Paciente paciente);
    List<Paciente> buscarPacientes();
    Paciente buscarPacientePorId(UUID id);
    Paciente atualizarPaciente(UUID id, Paciente paciente);
    void deletePaciente(UUID id);
}
