package br.com.clicanicaodontologica.clinica;

import br.com.clicanicaodontologica.clinica.api.dto.request.ContatoRequest;
import br.com.clicanicaodontologica.clinica.api.dto.request.EnderecoRequest;
import br.com.clicanicaodontologica.clinica.api.dto.request.PacienteRquest;
import br.com.clicanicaodontologica.clinica.domain.entity.GeneroEnum;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;
public final class Fixture {

    private static final Faker FAKER = new Faker(new Locale("pt", "BR"));

    public static class PacienteFake {
        public static PacienteRquest anyPaciente() {
            PacienteRquest request = new PacienteRquest();
            request.setNome(FAKER.rickAndMorty().character());
            request.setDataNascimento(LocalDate.now());
            request.setGenero(GeneroEnum.M);
            ContatoRequest contatoRequest = new ContatoRequest();
            contatoRequest.setEmail(FAKER.internet().emailAddress());
            contatoRequest.setTelefone("(11) 12345-6789");
            request.setContato(contatoRequest);
            EnderecoRequest enderecoRequest = new EnderecoRequest();
            enderecoRequest.setLogradouro(FAKER.address().streetAddress());
            enderecoRequest.setBairro(FAKER.address().secondaryAddress());
            enderecoRequest.setCidade(FAKER.address().cityName());
            enderecoRequest.setEstado(FAKER.address().state());
            enderecoRequest.setCep(FAKER.address().zipCode());
            request.setEndereco(enderecoRequest);
            return request;
        }
    }
}