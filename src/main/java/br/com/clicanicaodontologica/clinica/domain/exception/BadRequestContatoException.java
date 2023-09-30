package br.com.clicanicaodontologica.clinica.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestContatoException {
    public BadRequestContatoException() {
        super();
    }
}
