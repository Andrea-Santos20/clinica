package br.com.clicanicaodontologica.clinica.api.controller;

import br.com.clicanicaodontologica.clinica.api.dto.request.PacienteRquest;
import br.com.clicanicaodontologica.clinica.api.dto.response.ContatoResponse;
import br.com.clicanicaodontologica.clinica.api.dto.response.EnderecoResponse;
import br.com.clicanicaodontologica.clinica.api.dto.response.PacienteResponse;
import br.com.clicanicaodontologica.clinica.api.dto.response.listResponse.PacienteListResponse;
import br.com.clicanicaodontologica.clinica.api.dto.response.wrapperResponse.PacienteWrapperResponse;
import br.com.clicanicaodontologica.clinica.domain.entity.Contato;
import br.com.clicanicaodontologica.clinica.domain.entity.Endereco;
import br.com.clicanicaodontologica.clinica.domain.entity.Paciente;
import br.com.clicanicaodontologica.clinica.domain.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    @GetMapping
    ResponseEntity<PacienteWrapperResponse> buscarPacientes() {
        List<Paciente> pacientes = pacienteService.buscarPaciente();
        PacienteWrapperResponse pacienteWrapperResponse = new PacienteWrapperResponse();

        pacienteWrapperResponse.setPacientes( pacientes.stream().map( paciente -> {
            PacienteListResponse pacienteListResponse = new PacienteListResponse();
            pacienteListResponse.setId(paciente.getId());
            pacienteListResponse.setNome(paciente.getNome());
            return pacienteListResponse;
        }).toList());

        return ResponseEntity.ok(pacienteWrapperResponse);
    }
    @GetMapping("{id}")
    ResponseEntity<PacienteResponse> buscarPacientePorId(@PathVariable UUID id) {
        Paciente paciente = pacienteService.buscarPacientePorId(id);
        PacienteResponse response = pacienteResponseByPaciente(paciente);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    ResponseEntity<PacienteResponse> criarPaciente(@RequestBody @Valid PacienteRquest request) {

        Paciente paciente = new Paciente();
        paciente.setNome(request.getNome());
        paciente.setDataNascimento(request.getDataNascimento());
        paciente.setGenero(request.getGenero());

        Contato contato = new Contato();
        contato.setEmail(request.getContato().getEmail());
        contato.setTelefone(request.getContato().getTelefone());
        paciente.setContato(contato);

        Endereco endereco = new Endereco();
        endereco.setLogradouro(request.getEndereco().getLogradouro());
        endereco.setBairro(request.getEndereco().getBairro());
        endereco.setCidade(request.getEndereco().getCidade());
        endereco.setEstado(request.getEndereco().getEstado());
        endereco.setCep(request.getEndereco().getCep());
        paciente.setEndereco(endereco);

        Paciente pacienteCriado = pacienteService.criarPaciente(paciente);
        PacienteResponse response = pacienteResponseByPaciente(pacienteCriado);
        return ResponseEntity.ok(response);
    }
    @PutMapping("{id}")
    ResponseEntity<PacienteResponse> atualizarPaciente(@PathVariable UUID id, @RequestBody @Valid PacienteRquest request) {
        Paciente paciente = pacienteService.buscarPacientePorId(id);

        paciente.setNome(request.getNome());
        paciente.setDataNascimento(request.getDataNascimento());
        paciente.setGenero(request.getGenero());

        Contato contato = new Contato();
        contato.setEmail(request.getContato().getEmail());
        contato.setTelefone(request.getContato().getTelefone());
        paciente.setContato(contato);

        Endereco endereco = new Endereco();
        endereco.setLogradouro(request.getEndereco().getLogradouro());
        endereco.setBairro(request.getEndereco().getBairro());
        endereco.setCidade(request.getEndereco().getCidade());
        endereco.setEstado(request.getEndereco().getEstado());
        endereco.setCep(request.getEndereco().getCep());
        paciente.setEndereco(endereco);

        Paciente pacienteAtualizado = pacienteService.atualizarPaciente(id, paciente);
        PacienteResponse response = pacienteResponseByPaciente(pacienteAtualizado);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("{id}")
    ResponseEntity<Void> deletePaciente(@PathVariable UUID id) {
        pacienteService.deletePaciente(id);
        return ResponseEntity.ok().build();
    }

    private PacienteResponse pacienteResponseByPaciente(Paciente paciente) {

        PacienteResponse pacienteResponse = new PacienteResponse();
        pacienteResponse.setId(paciente.getId());
        pacienteResponse.setNome(paciente.getNome());
        pacienteResponse.setDataNascimento(paciente.getDataNascimento());
        pacienteResponse.setGenero(paciente.getGenero());
        pacienteResponse.setCreatedAt(Instant.from(paciente.getCreatedAt()));
        pacienteResponse.setUpdateAt(Instant.from(paciente.getUpdateAt()));

        ContatoResponse contato = new ContatoResponse();
        contato.setId(paciente.getContato().getId());
        contato.setEmail(paciente.getContato().getEmail());
        contato.setTelefone(paciente.getContato().getTelefone());
        contato.setCreatedAt(Instant.from(paciente.getContato().getCreatedAt()));
        contato.setUpdateAt(Instant.from(paciente.getContato().getUpdateAt()));

        EnderecoResponse endereco = new EnderecoResponse();
        endereco.setId(paciente.getEndereco().getId());
        endereco.setLogradouro(paciente.getEndereco().getLogradouro());
        endereco.setBairro(paciente.getEndereco().getBairro());
        endereco.setCidade(paciente.getEndereco().getCidade());
        endereco.setEstado(paciente.getEndereco().getEstado());
        endereco.setCep(paciente.getEndereco().getCep());
        endereco.setCreatedAt(Instant.from(paciente.getEndereco().getCreatedAt()));
        endereco.setUpdateAt(Instant.from(paciente.getEndereco().getUpdateAt()));

        pacienteResponse.setContato(contato);
        pacienteResponse.setEndereco(endereco);

        return pacienteResponse;
    }
}
