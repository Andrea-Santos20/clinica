package br.com.clicanicaodontologica.clinica.domain.service.impl;

import br.com.clicanicaodontologica.clinica.domain.entity.Dentista;
import br.com.clicanicaodontologica.clinica.domain.repository.DentistaRepository;
import br.com.clicanicaodontologica.clinica.domain.service.DentistaSertvice;
import br.com.clicanicaodontologica.clinica.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DentistaServiceImpl implements DentistaSertvice {

    private final DentistaRepository dentistaRepository;
    @Autowired
    public DentistaServiceImpl(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }
    @Override
    public Dentista criarDentista(Dentista dentista) {
        return dentistaRepository.save(dentista);
    }
    @Override
    public List<Dentista> buscarDentista() {
        return dentistaRepository.findAll();
    }
    @Override
    public Dentista buscarDentistaPorId(UUID id) throws NotFoundException {
        try {
            return dentistaRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new NotFoundException((id));
        }
    }
    @Override
    public Dentista atualizarDentistas(UUID id, Dentista dentista) throws NotFoundException {
        try {
            dentistaRepository.findById(id);
        } catch (Exception e) {
            throw new NotFoundException((id));
        }
        return dentistaRepository.save(dentista);
    }
    @Override
    public void deleteDentista(UUID id) throws NotFoundException {
        try {
            dentistaRepository.findById(id).orElseThrow();
            dentistaRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException((id));
        }
    }
}
