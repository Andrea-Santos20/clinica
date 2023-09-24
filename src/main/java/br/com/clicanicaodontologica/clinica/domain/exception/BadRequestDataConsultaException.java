package br.com.clicanicaodontologica.clinica.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestDataConsultaException extends RuntimeException {
    public BadRequestDataConsultaException(LocalDate data) {
        super("A data: " + data + " é anterior a data atual");
    }
}
