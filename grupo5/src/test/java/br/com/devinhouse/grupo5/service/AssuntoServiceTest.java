package br.com.devinhouse.grupo5.service;

import br.com.devinhouse.grupo5.domain.exceptions.AssuntoNaoEncontradoException;
import br.com.devinhouse.grupo5.dto.AssuntoInputDTO;
import br.com.devinhouse.grupo5.dto.AssuntoOutputDTO;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@SpringBootTest
class AssuntoServiceTest {

  @Autowired
  AssuntoService assuntoService;

  @Test
  void cadastrarAssunto () {
    AssuntoInputDTO assuntoInputDTO = new AssuntoInputDTO("Teste unitário", Date.from(Instant.now()), true);

    AssuntoOutputDTO assuntoOutputDTO = assuntoService.cadastrarAssunto(assuntoInputDTO);

    AssertionsForClassTypes.assertThat(assuntoOutputDTO).isInstanceOf(AssuntoOutputDTO.class);
  }

  @Test
  void buscarAssuntoPorIdNaoExistente () {
    Throwable erro = catchThrowable(() -> {

      assuntoService.buscarAssuntoPorId(99L);
    });
    assertThat(erro)
            .isInstanceOf(AssuntoNaoEncontradoException.class);
  }
}