package br.com.clicanicaodontologica.clinica;

import br.com.clicanicaodontologica.clinica.api.dto.request.PacienteRquest;
import br.com.clicanicaodontologica.clinica.domain.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
class ProjetoClinicaApplicationTests {
	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper objectMapper;
	@SpyBean
	private PacienteService pacienteService;
	@Test
	void contextLoads() {
		Assertions.assertNotNull(mvc);
	}
	@Test
	@DisplayName("Com um objeto válido ao chamarmos o endpoint criar Pacientes, retorne o Objeto Mockado")
	void dadoUmObjetoValido_quandoChamamosEndpointCriarPaciente_entaoRetornarObjetoMockado() throws Exception {

		PacienteRquest paciente = Fixture.PacienteFake.anyPaciente();

		String pacienteAsJson = objectMapper.writeValueAsString(paciente);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post("/v1/pacientes")
				.content(pacienteAsJson)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(request)
				.andDo(print())
				.andExpect(status().isOk());
	}
	@Test
	@DisplayName("Ao chamarmos um endpoint buscar Pacientes, retorne uma lista vazia")
	void dadoUmaListaVazia_quandoChamamosEndpointBuscarPacientes_entaoRetornarListaVazia() throws Exception {
		Mockito.when(pacienteService.buscarPacientes())
				.thenReturn(new ArrayList<>());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get("/v1/pacientes")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(request)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}

}