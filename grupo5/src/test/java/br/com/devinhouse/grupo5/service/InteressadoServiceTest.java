package br.com.devinhouse.grupo5.service;

import br.com.devinhouse.grupo5.domain.exceptions.InteressadoNaoEncontradoException;
import br.com.devinhouse.grupo5.dto.InteressadoInputDTO;
import br.com.devinhouse.grupo5.dto.InteressadoOutputDTO;
import br.com.devinhouse.grupo5.model.Interessado;
import br.com.devinhouse.grupo5.repository.InteressadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InteressadoServiceTest {

    @InjectMocks
    InteressadoService interessadoService;

    @Mock
    InteressadoRepository interessadoRepository;

    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        interessadoRepository.deleteAll();
    }

    @Test
    public void verificarOCadastroDeUmInteressadoComInformacoesValidas(){

        //given
        InteressadoInputDTO interessadoInputDTO = new InteressadoInputDTO(
                "Fulano",
                "12345678910",
                Date.valueOf("2000-12-12"),
                true
        );

        Interessado interessado = new Interessado(
                1L,
                interessadoInputDTO.getNmInteressado(),
                interessadoInputDTO.getNuIdentificacao(),
                interessadoInputDTO.getDtNascimento(),
                interessadoInputDTO.getFlAtivo()
        );

        //when
        when(modelMapper.map(interessadoInputDTO, Interessado.class)).thenReturn(interessado);
        when(interessadoRepository.save(interessado)).thenReturn(interessado);

        InteressadoOutputDTO interessadoOutputDTO = interessadoService.cadastrarInteressado(interessadoInputDTO);

        //then
        assertAll(
                () -> verify(interessadoRepository).save(interessado)
        );
    }

    @Test
    public void deveRetornarErroQuandoBuscarPorUmNumeroDeIdentificacaoInexistente(){
        Throwable exception = catchThrowable(() -> interessadoService.buscarInteressadoPeloNuIdentificacao("12345678910"));

        assertThat(exception).isInstanceOf(InteressadoNaoEncontradoException.class);
    }

    @Test
    public void deveRetornarErroQuandoBuscarPorUmIdInexistente(){
        Throwable exception = catchThrowable(() -> interessadoService.buscarInteressadoPeloId(1L));

        assertThat(exception).isInstanceOf(InteressadoNaoEncontradoException.class);
    }

}
