package br.com.clicanicaodontologica.clinica.domain.service.impl;

import br.com.clicanicaodontologica.clinica.domain.entity.Consulta;
import br.com.clicanicaodontologica.clinica.domain.exception.BadRequestDataConsultaException;
import br.com.clicanicaodontologica.clinica.domain.repository.ConsultaRepository;
import br.com.clicanicaodontologica.clinica.domain.service.ConsultaService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;
    public ConsultaServiceImpl(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }
    @Override
    public Consulta criarConsulta(Consulta consulta) {
        if(consulta.getDataConsulta().isBefore(LocalDate.now())) {
            throw new BadRequestDataConsultaException(consulta.getDataConsulta());
        }
       return consultaRepository.save(consulta);
    }
    @Override
    public List<Consulta> buscarConsultas() {
        return consultaRepository.findAll();
    }
    @Override
    public Consulta buscarConsultaPorId(UUID id) {
        return consultaRepository.findById(id).orElseThrow();
    }
    @Override
    public Consulta atualizarConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    @Override
    public void deleteConsulta(UUID id) {
        consultaRepository.deleteById(id);
    }
}