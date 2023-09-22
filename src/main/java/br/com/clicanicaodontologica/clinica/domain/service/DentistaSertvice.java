package br.com.clicanicaodontologica.clinica.domain.service;

import br.com.clicanicaodontologica.clinica.domain.entity.Dentista;
import br.com.clicanicaodontologica.clinica.domain.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface DentistaSertvice {
   Dentista criarDentista (Dentista dentista);
    List<Dentista> buscarDentista();
    Dentista buscarDentistaPorId(UUID id) throws NotFoundException;
    Dentista atualizarDentista(Dentista dentista);
    Dentista atualizarDentista(UUID id, Dentista dentista) throws NotFoundException;
    void deleteDentista(UUID id) throws NotFoundException;
}
