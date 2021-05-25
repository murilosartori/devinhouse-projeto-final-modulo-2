package br.com.devinhouse.grupo5.service;

import br.com.devinhouse.grupo5.domain.exceptions.AssuntoNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@SpringBootTest
class AssuntoServiceTest {

  @Autowired
  AssuntoService assuntoService;

//  @Autowired
//  AssuntoRepository assuntoRepository;

  @Test
  void cadastrarAssunto () {
    // given
    // dado que eu tento salvar um assunto
    // when
    // quando tentar salvar
    // then
    // deve devolver um outputDTO do assunto salvo
  }

  @Test
  void buscarAssuntoPorIdNaoExistente () {
    // given
    //
    // when
    // then
    // AssuntoNaoEncontradoException
    // assertThrows()
    //assertThatThrownBy(() -> assuntoService.buscarAssuntoPorId(99L))
    //  .isInstanceOf(AssuntoNaoEncontradoException.class);
    Throwable erro = catchThrowable(() -> {
//      throw new Exception("oi");

      assuntoService.buscarAssuntoPorId(99L);
    });
    assertThat(erro)
      .isInstanceOf(AssuntoNaoEncontradoException.class);
//      .isInstanceOf(Exception.class);
//        .hasMessageContaining("oi")
  }
}