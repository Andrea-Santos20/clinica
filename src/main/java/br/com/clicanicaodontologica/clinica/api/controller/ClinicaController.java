package br.com.clicanicaodontologica.clinica.api.controller;

import br.com.clicanicaodontologica.clinica.api.dto.request.ClinicaRequest;
import br.com.clicanicaodontologica.clinica.api.dto.response.ClinicaResponse;
import br.com.clicanicaodontologica.clinica.api.dto.response.listResponse.ClinicaListResponse;
import br.com.clicanicaodontologica.clinica.domain.entity.Clinica;
import br.com.clicanicaodontologica.clinica.domain.entity.Contato;
import br.com.clicanicaodontologica.clinica.domain.entity.Endereco;
import br.com.clicanicaodontologica.clinica.domain.service.ClinicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/clinica")

public class ClinicaController {

    private final ClinicaService clinicaService;

   @Autowired
    public ClinicaController(ClinicaService clinicaService) {
        this.clinicaService = clinicaService;
    }
   @GetMapping
   <ClinicaResponseWrapper>
    ResponseEntity<ClinicaResponseWrapper> buscarClinicas() {
        List<Clinica> clinicas = clinicaService.buscarClinicas();
        ClinicaResponseWrapper clinicaResponseWrapper = new ClinicaResponseWrapper();
        clinicaResponseWrapper.setClinicas(clinicas.stream().map( clinica -> {
            ClinicaListResponse clinicaListResponse = new ClinicaListResponse();
            clinicaListResponse.setId(clinica.getId());
            clinicaListResponse.setCnpj(clinica.getCnpj());
            clinicaListResponse.setNome(clinica.getNome());
            return clinicaListResponse;
        }).toList());
        return ResponseEntity.ok(clinicaResponseWrapper);
    }
    @GetMapping("{id}")
    ResponseEntity<ClinicaResponse> buscarPorId(@PathVariable UUID id) {
        Clinica clinica = clinicaService.buscarClinicaPorId(id);
        ClinicaResponse response = clinicaResponseByClinica(clinica);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    ResponseEntity<ClinicaResponse> criarClinica(@RequestBody @Valid ClinicaRequest request) {
        Clinica clinica = new Clinica();

        clinica.setCnpj(request.getCnpj());
        clinica.setNome(request.getNome());
        clinica.setRazaoSocial(request.getRazaoSocial());
        clinica.setDescricao(request.getDescricao());

        Contato contato = new Contato();
        contato.setEmail(request.getContato().getEmail());
        contato.setTelefone(request.getContato().getTelefone());
        clinica.setContato(contato);

        Endereco endereco = new Endereco();
        endereco.setLogradouro(request.getEndereco().getLogradouro());
        endereco.setBairro(request.getEndereco().getBairro());
        endereco.setCidade(request.getEndereco().getCidade());
        endereco.setEstado(request.getEndereco().getEstado());
        endereco.setCep(request.getEndereco().getCep());
        clinica.setEndereco(endereco);

        Clinica clinicaCriada = clinicaService.criarClinica(clinica);
        ClinicaResponse response = clinicaResponseByClinica(clinicaCriada);
        return ResponseEntity.ok(response);
    }
    @PutMapping("{id}")
    ResponseEntity<ClinicaResponse> atualizarClinica(@PathVariable UUID id, @RequestBody @Valid ClinicaRequest request) {

        Clinica clinica = clinicaService.buscarClinicaPorId(id);
        clinica.setCnpj(request.getCnpj());
        clinica.setNome(request.getNome());
        clinica.setRazaoSocial(request.getRazaoSocial());
        clinica.setDescricao(request.getDescricao());

        Contato contato = new Contato();
        contato.setEmail(request.getContato().getEmail());
        contato.setTelefone(request.getContato().getTelefone());
        clinica.setContato(contato);

        Endereco endereco = new Endereco();
        endereco.setLogradouro(request.getEndereco().getLogradouro());
        endereco.setBairro(request.getEndereco().getBairro());
        endereco.setCidade(request.getEndereco().getCidade());
        endereco.setEstado(request.getEndereco().getEstado());
        endereco.setCep(request.getEndereco().getCep());
        clinica.setEndereco(endereco);

        Clinica clinicaAtualizada = clinicaService.atualizarClinica(id, clinica);
        ClinicaResponse response = clinicaResponseByClinica(clinicaAtualizada);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deletarClinica(@PathVariable UUID id) {
        clinicaService.deletarClinica(id);
        return ResponseEntity.ok().build();
    }

    private ClinicaResponse clinicaResponseByClinica(Clinica clinica) {
        ClinicaResponse clinicaResponse = new ClinicaResponse();
        clinicaResponse.setId(clinica.getId());
        clinicaResponse.setNome(clinica.getNome());
        clinicaResponse.setCnpj(clinica.getCnpj());
        clinicaResponse.setRazaoSocial(clinica.getRazaoSocial());
        clinicaResponse.setCreatedAt(clinica.getCreatedAt());
        clinicaResponse.setUpdateAt(clinica.getUpdateAt());
        clinicaResponse.setDescricao(clinica.getDescricao());

        ContatoResponse contato = new ContatoResponse();
        contato.setId(clinica.getContato().getId());
        contato.setEmail(clinica.getContato().getEmail());
        contato.setTelefone(clinica.getContato().getTelefone());
        contato.setCreatedAt(clinica.getContato().getCreatedAt());
        contato.setUpdateAt(clinica.getContato().getUpdateAt());

        EnderecoResponse endereco = new EnderecoResponse();
        endereco.setId(clinica.getEndereco().getId());
        endereco.setLogradouro(clinica.getEndereco().getLogradouro());
        endereco.setBairro(clinica.getEndereco().getBairro());
        endereco.setCidade(clinica.getEndereco().getCidade());
        endereco.setEstado(clinica.getEndereco().getEstado());
        endereco.setCep(clinica.getEndereco().getCep());
        endereco.setCreatedAt(clinica.getEndereco().getCreatedAt());
        endereco.setUpdateAt(clinica.getEndereco().getUpdateAt());

        clinicaResponse.setContato(contato);
        clinicaResponse.setEndereco(endereco);

        return clinicaResponse;
    }
}

