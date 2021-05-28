package br.com.devinhouse.grupo5.controllers;

import br.com.devinhouse.grupo5.dto.AssuntoOutputDTO;
import br.com.devinhouse.grupo5.dto.InteressadoInputDTO;
import br.com.devinhouse.grupo5.dto.InteressadoOutputDTO;
import br.com.devinhouse.grupo5.service.InteressadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InteressadoController.class)
class InteressadoControllerTest {

  private final String BASE_URL = "/v1/interessado";

  @Autowired
	private MockMvc mockMvc;

	@MockBean
	private InteressadoService service;

  @Autowired
  private ObjectMapper objectMapper;


  InteressadoInputDTO interessadoInput = new InteressadoInputDTO();
  InteressadoOutputDTO interessadoOutput = new InteressadoOutputDTO();

  @Test
  void cadastrarAssuntoDeveRetornarHttpCreated () throws Exception {
    // Given
    MockHttpServletRequestBuilder request = post(BASE_URL)
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .content(objectMapper.writeValueAsString(interessadoInput));

    // When
    when(service.cadastrarInteressado(any())).thenReturn(interessadoOutput);
    this.mockMvc.perform(request) // Then
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isCreated());
  }

  @Test
  void buscarInteressadoPeloIdDeveRetornarJsonEHttpOk () throws Exception {
    // Given
    MockHttpServletRequestBuilder request = get(BASE_URL+"/id/1");
    // When
    when(service.buscarInteressadoPeloId(1L)).thenReturn(interessadoOutput);
    this.mockMvc.perform(request) // Then
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk());
  }

  @Test
  void buscarInteressadoPeloNuIdentificacao () throws Exception {
    // Given
    final String TESTE_DO_PARAMETRO = "testedoparametro";
    MockHttpServletRequestBuilder request = get(BASE_URL+"/nuidentificacao?valor="+TESTE_DO_PARAMETRO);
    // When
    when(service.buscarInteressadoPeloNuIdentificacao(TESTE_DO_PARAMETRO)).thenReturn(interessadoOutput);
    this.mockMvc.perform(request) // Then
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk());
  }
}