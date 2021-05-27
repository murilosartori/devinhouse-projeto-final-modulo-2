package br.com.devinhouse.grupo5.controllers;

import br.com.devinhouse.grupo5.service.AssuntoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(AssuntoController.class)
public class AssuntoControllerTest {

  @Autowired
	private MockMvc mockMvc;

	@MockBean
	private AssuntoService service;

  @Test
  public void cadastrarAssuntoDeveRetornarHttpCreated () throws Exception {
    // Given

    // When
    this.mockMvc.perform(get("/v1/assunto").content())
      .andExpect(status().isOk());
//      .andDo(print())
//      .andExpect(content().string(containsString("Hello, Mock")));
  }
    // Then
  }

  @Test
  void buscarAssuntoPorId () {
    // Given
    // When
    // Then
  }
}