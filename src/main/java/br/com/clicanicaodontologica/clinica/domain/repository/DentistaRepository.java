package br.com.clicanicaodontologica.clinica.domain.repository;

import br.com.clicanicaodontologica.clinica.domain.entity.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DentistaRepository extends JpaRepository<Dentista, UUID> {
}
