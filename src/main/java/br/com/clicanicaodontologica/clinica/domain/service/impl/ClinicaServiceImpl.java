package br.com.clicanicaodontologica.clinica.domain.service.impl;

import br.com.clicanicaodontologica.clinica.domain.entity.Clinica;
import br.com.clicanicaodontologica.clinica.domain.exception.BadRequestCnpjException;
import br.com.clicanicaodontologica.clinica.domain.exception.NotFoundException;
import br.com.clicanicaodontologica.clinica.domain.repository.ClinicaRepository;
import br.com.clicanicaodontologica.clinica.domain.service.ClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClinicaServiceImpl implements ClinicaService {
    private final ClinicaRepository clinicaRepository;
    @Autowired
    public ClinicaServiceImpl(ClinicaRepository clinicaRepository) {

        this.clinicaRepository = clinicaRepository;
    }
    @Override
    public Clinica criarClinica(Clinica clinica) {
        boolean cnpjExiste = clinicaRepository.existsByCnpi(clinica.getCnpj());
        if(cnpjExiste){
                throw new BadRequestCnpjException(clinica.getCnpj());
        }
        return clinicaRepository.save(clinica);
    }
    @Override
    public List<Clinica> buscarClinicas() {
        return clinicaRepository.findAll();
    }
    @Override
    public Clinica buscarClinicaPorId(UUID id) throws NotFoundException {
        try{
            return clinicaRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
    }
    @Override
    public Clinica atualizarClinica(Clinica clinica) {
        return null;
    }
    @Override
    public Clinica atualizarClinica(UUID id, Clinica clinica) throws NotFoundException {
        try{
            clinicaRepository.findById(id);
        } catch (Exception e){
            throw new NotFoundException(id);
        }
        return clinicaRepository.save(clinica);
    }
    @Override
    public void deletarClinica(UUID id) throws NotFoundException {
        try {
            clinicaRepository.findById(id).orElseThrow();
            clinicaRepository.deleteById(id);
        } catch (Exception e){
            throw new NotFoundException(id);
        }
    }
}
