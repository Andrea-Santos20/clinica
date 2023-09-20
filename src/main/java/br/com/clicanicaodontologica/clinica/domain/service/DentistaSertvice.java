package br.com.clicanicaodontologica.clinica.domain.service;

import br.com.clicanicaodontologica.clinica.domain.entity.Dentista;

import java.util.List;
import java.util.UUID;

public interface DentistaSertvice {
   Dentista criarDentista (Dentista dentista);
    List<Dentista> buscarDentista();
    Dentista buscarDentistaPorId(UUID id);
    Dentista atualizarDentista(Dentista dentista);
    void deleteDentista(UUID id);
}
