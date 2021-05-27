package br.com.devinhouse.grupo5.service;

import br.com.devinhouse.grupo5.domain.exceptions.AssuntoNaoEncontradoException;
import br.com.devinhouse.grupo5.dto.AssuntoInputDTO;
import br.com.devinhouse.grupo5.dto.AssuntoOutputDTO;
import br.com.devinhouse.grupo5.model.Assunto;
import br.com.devinhouse.grupo5.repository.AssuntoRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssuntoServiceTest {

  @InjectMocks
  private AssuntoService assuntoService;

  @Mock
  private AssuntoRepository assuntoRepository;

  @Mock
  private ModelMapper modelMapper;

  @Test
  void cadastrarAssunto() {

    AssuntoInputDTO assuntoInputDTO = new AssuntoInputDTO(
            "Teste unitário",
           LocalDate.parse("2021-05-26"),
            true
    );

    Assunto assunto = new Assunto(
            1L,
            assuntoInputDTO.getDescricao(),
            assuntoInputDTO.getDtCadastro(),
            assuntoInputDTO.getFlAtivo()
    );

    AssuntoOutputDTO assuntoOutputDTO = new AssuntoOutputDTO(
            assunto.getId(),
            assunto.getDescricao(),
            assunto.getDtCadastro(),
            assunto.getFlAtivo()
    );

    when(modelMapper.map(assuntoInputDTO, Assunto.class)).thenReturn(assunto);
    when(assuntoRepository.save(assunto)).thenReturn(assunto);
    when(modelMapper.map(assunto, AssuntoOutputDTO.class)).thenReturn(assuntoOutputDTO);

    AssuntoOutputDTO expected = assuntoService.cadastrarAssunto(assuntoInputDTO);

    AssertionsForClassTypes.assertThat(expected).isInstanceOf(AssuntoOutputDTO.class);
  }

  @Test
  void buscarAssuntoPorIdNaoExistente() {
    Throwable erro = catchThrowable(() -> assuntoService.buscarAssuntoPorId(1L));

    assertThat(erro)
            .isInstanceOf(AssuntoNaoEncontradoException.class);
  }

  @Test
  void deveRetornarUmAssuntoQuandoBuscarPorUmIdExistente(){

    Assunto assunto = new Assunto(
            1L,
            "Teste unitário",
            LocalDate.parse("2021-05-26"),
            true
    );

    when(assuntoRepository.findById(any())).thenReturn(Optional.of(assunto));

    assuntoService.buscarAssuntoPorId(assunto.getId());

    verify(assuntoRepository, times(1)).findById(assunto.getId());
  }
}