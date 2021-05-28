package br.com.devinhouse.grupo5.controllers;

import br.com.devinhouse.grupo5.dto.ProcessoInputDTO;
import br.com.devinhouse.grupo5.dto.ProcessoOutputDTO;
import br.com.devinhouse.grupo5.service.ProcessoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProcessoController.class)
class ProcessoControllerTest {

  ProcessoControllerTest () throws JsonProcessingException {
  }

  private final String BASE_URL = "/v1/processo";

  @Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProcessoService service;

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final ProcessoInputDTO processoInput = new ProcessoInputDTO();
  private final ProcessoOutputDTO processoOutput = new ProcessoOutputDTO();
  private final List<ProcessoOutputDTO> listaDeProcessosOutput = List.of(processoOutput);
  private final String listaDeProcessosOutputString = objectMapper.writeValueAsString(listaDeProcessosOutput);
  private final String processoInputString = objectMapper.writeValueAsString(processoInput);
  private final String processoOutputString = objectMapper.writeValueAsString(processoOutput);
  private final long TESTE_PARAMETRO_LONG = 1L;
  private final String TESTE_PARAMETRO_STRING = "testedoparametro";

  @Test
  void criaProcesso () throws Exception {
    // Given
    MockHttpServletRequestBuilder request = post(BASE_URL)
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .content(processoInputString);

    // When
    when(service.salvarProcesso(any())).thenReturn(processoOutput);
    this.mockMvc.perform(request) // Then
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string(processoOutputString))
      .andExpect(status().isCreated());
  }

  @Test
  void listaProcessos () throws Exception {
    // Given
    MockHttpServletRequestBuilder request = get(BASE_URL);
    // When
    when(service.buscarTodosProcessos()).thenReturn(listaDeProcessosOutput);
    this.mockMvc.perform(request) // Then
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string(listaDeProcessosOutputString))
      .andExpect(status().isOk());
  }

  @Test
  void buscaUmProcesso () throws Exception {
    // Given
    MockHttpServletRequestBuilder request = get(BASE_URL+"/id/"+TESTE_PARAMETRO_LONG);
    // When
    when(service.buscarUmProcesso(TESTE_PARAMETRO_LONG)).thenReturn(processoOutput);
    this.mockMvc.perform(request) // Then
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string(processoOutputString))
      .andExpect(status().isOk());
  }

  @Test
  void buscaUmProcessoPorChave () throws Exception {
    // Given
    MockHttpServletRequestBuilder request = get(BASE_URL+"/chaveprocesso?q="+TESTE_PARAMETRO_STRING);
    // When
    when(service.buscarUmProcessoPorChave(TESTE_PARAMETRO_STRING)).thenReturn(processoOutput);
    this.mockMvc.perform(request) // Then
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string(processoOutputString))
      .andExpect(status().isOk());
  }

  @Test
  void buscaUmProcessoPorInteressado () throws Exception {
    // Given
    MockHttpServletRequestBuilder request = get(BASE_URL+"/cdinteressado?q="+TESTE_PARAMETRO_LONG);
    // When
    when(service.buscarUmProcessoPorCdInteressado(TESTE_PARAMETRO_LONG)).thenReturn(listaDeProcessosOutput);
    this.mockMvc.perform(request) // Then
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string(listaDeProcessosOutputString))
      .andExpect(status().isOk());
  }

  @Test
  void buscaUmProcessoPorAssunto () throws Exception {
    // Given
    MockHttpServletRequestBuilder request = get(BASE_URL+"/cdassunto?q="+TESTE_PARAMETRO_LONG);
    // When
    when(service.buscarUmProcessoPorCdAssunto(TESTE_PARAMETRO_LONG)).thenReturn(listaDeProcessosOutput);
    this.mockMvc.perform(request) // Then
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string(listaDeProcessosOutputString))
      .andExpect(status().isOk());
  }

  @Test
  void atualizaProcesso () throws Exception {
    // Given
    MockHttpServletRequestBuilder request = put(BASE_URL+"/id/"+TESTE_PARAMETRO_LONG)
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .content(processoInputString);
    // When
    this.mockMvc.perform(request) // Then
      .andExpect(status().isNoContent());
  }

  @Test
  void deletaProcesso () throws Exception {
    // Given
    MockHttpServletRequestBuilder request = delete(BASE_URL+"/id/"+TESTE_PARAMETRO_LONG)
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .content(processoInputString);
    // When
    when(service.deletarProcesso(TESTE_PARAMETRO_LONG)).thenReturn(processoOutput);
    this.mockMvc.perform(request) // Then
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string(processoOutputString))
      .andExpect(status().isOk());
  }
}