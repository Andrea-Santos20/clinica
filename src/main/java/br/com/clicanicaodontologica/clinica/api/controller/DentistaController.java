package br.com.clicanicaodontologica.clinica.api.controller;

import br.com.clicanicaodontologica.clinica.api.dto.request.DentistaRequest;
import br.com.clicanicaodontologica.clinica.api.dto.response.ContatoResponse;
import br.com.clicanicaodontologica.clinica.api.dto.response.DentistaResponse;
import br.com.clicanicaodontologica.clinica.api.dto.response.listResponse.DentistaListResponse;
import br.com.clicanicaodontologica.clinica.api.dto.response.wrapperResponse.DentistaWrapperResponse;
import br.com.clicanicaodontologica.clinica.domain.entity.Contato;
import br.com.clicanicaodontologica.clinica.domain.entity.Dentista;
import br.com.clicanicaodontologica.clinica.domain.exception.NotFoundException;
import br.com.clicanicaodontologica.clinica.domain.service.DentistaSertvice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/dentistas")
public class DentistaController {
    private final DentistaSertvice dentistaSertvice;

    @Autowired
    public DentistaController(DentistaSertvice dentistaSertvice) {
        this.dentistaSertvice = dentistaSertvice;
    }
    @GetMapping
    ResponseEntity<DentistaWrapperResponse> buscarDentistas() {
        List<Dentista> dentistas = dentistaSertvice.buscarDentista();
        DentistaWrapperResponse dentistaWrapperResponse = new DentistaWrapperResponse();

        dentistaWrapperResponse.setDentistas(dentistas.stream().map(dentista -> {
            DentistaListResponse dentistaListResponse = new DentistaListResponse();
            dentistaListResponse.setId(dentista.getId());
            dentistaListResponse.setNome(dentista.getNome());
            dentistaListResponse.setCro(dentista.getCro());
            return dentistaListResponse;
        }).toList());

        return ResponseEntity.ok(dentistaWrapperResponse);
    }

    @GetMapping("{id}")
    ResponseEntity<DentistaResponse> buscarDentistaPorId(@PathVariable UUID id) throws NotFoundException {
        Dentista dentista = dentistaSertvice.buscarDentistaPorId(id);
        DentistaResponse response = dentistaResponseByDentista(dentista);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<DentistaResponse> criarDentista(@RequestBody @Valid DentistaRequest request) {
        Dentista dentista = new Dentista();
        dentista.setNome(request.getNome());
        dentista.setCro(request.getCro());
        dentista.setDataNascimento(request.getDataNascimento());
        dentista.setGenero(request.getGenero());

        Contato contato = new Contato();
        contato.setEmail(request.getContato().getEmail());
        contato.setTelefone(request.getContato().getTelefone());
        dentista.setContato(contato);

        dentista.setClinicaDentista(request.getClinicasDentistas());

        Dentista dentistaCriado = dentistaSertvice.criarDentista(dentista);
        DentistaResponse response = dentistaResponseByDentista(dentistaCriado);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    ResponseEntity<DentistaResponse> atualizarDentista(@PathVariable UUID id, @RequestBody DentistaRequest request) throws NotFoundException {

        Dentista dentista = dentistaSertvice.buscarDentistaPorId(id);

        dentista.setNome(request.getNome());
        dentista.setCro(request.getCro());
        dentista.setDataNascimento(request.getDataNascimento());
        dentista.setEspecialidade(request.getEspecialidade());
        dentista.setGenero(request.getGenero());

        Contato contato = new Contato();
        contato.setEmail(request.getContato().getEmail());
        contato.setTelefone(request.getContato().getTelefone());
        dentista.setContato(contato);

        dentista.setClinicaDentista(request.getClinicasDentistas());

        Dentista dentistaAtualizado = dentistaSertvice.criarDentista(dentista);
        DentistaResponse response = dentistaResponseByDentista(dentistaAtualizado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deletarDentista(@PathVariable UUID id) throws NotFoundException {
        dentistaSertvice.deleteDentista(id);
        return ResponseEntity.ok().build();
    }

    private DentistaResponse dentistaResponseByDentista(Dentista dentista) {

        DentistaResponse dentistaResponse = new DentistaResponse();
        dentistaResponse.setId(dentista.getId());
        dentistaResponse.setNome(dentista.getNome());
        dentistaResponse.setCro(dentista.getCro());
        dentistaResponse.setDataNascimento(LocalDate.from(dentista.getDataNascimento()));
        dentistaResponse.setEspecialidade(dentista.getEspecialidade());
        dentistaResponse.setGenero(dentista.getGenero());
        dentistaResponse.setCreatedAt(dentista.getCreatedAt());
        dentistaResponse.setUpdateAt(dentista.getUpdateAt());

        ContatoResponse contato = new ContatoResponse();
        contato.setId(dentista.getContato().getId());
        contato.setEmail(dentista.getContato().getEmail());
        contato.setTelefone(dentista.getContato().getTelefone());
        contato.setCreatedAt(dentista.getContato().getCreatedAt());
        contato.setUpdateAt(dentista.getContato().getUpdateAt());

        dentistaResponse.setContato(contato);
        dentistaResponse.setClinicasDentistas(dentista.getClinicaDentista());

        return dentistaResponse;
    }

}
