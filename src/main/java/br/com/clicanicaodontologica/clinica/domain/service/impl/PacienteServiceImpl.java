package br.com.clicanicaodontologica.clinica.domain.service.impl;

import br.com.clicanicaodontologica.clinica.domain.entity.Paciente;
import br.com.clicanicaodontologica.clinica.domain.repository.PacienteRepository;
import br.com.clicanicaodontologica.clinica.domain.service.PacienteService;
import org.openqa.selenium.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class PacienteServiceImpl implements PacienteService {
    private final PacienteRepository pacienteRepository;
    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    @Override
    public Paciente criarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
    @Override
    public List<Paciente> buscarPaciente() {
        return pacienteRepository.findAll();
    }
    @Override
    public Paciente buscarPacientePorId(UUID id) {
        try {
            return pacienteRepository.findById(id).orElseThrow();
        } catch (Exception e){
            throw new NotFoundException(String.valueOf(id));
        }
    }
    @Override
    public Paciente atualizarPaciente(Paciente paciente) {
        return null;
    }
    @Override
    public Paciente atualizarPaciente(UUID id, Paciente paciente) {
        try {
            pacienteRepository.findById(id);
        } catch (Exception e) {
            throw new NotFoundException(String.valueOf(id));
        }
        return pacienteRepository.save(paciente);
    }

    @Override
    public void deletePaciente(UUID id) {
        try {
            pacienteRepository.findById(id).orElseThrow();
            pacienteRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException(String.valueOf(id));
        }
    }
}
