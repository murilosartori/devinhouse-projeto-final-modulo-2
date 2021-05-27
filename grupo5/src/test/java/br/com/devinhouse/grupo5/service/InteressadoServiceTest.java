package br.com.devinhouse.grupo5.service;

import br.com.devinhouse.grupo5.domain.exceptions.CpfInvalidoException;
import br.com.devinhouse.grupo5.domain.exceptions.DataDeNascimentoInvalidaException;
import br.com.devinhouse.grupo5.domain.exceptions.InteressadoNaoEncontradoException;
import br.com.devinhouse.grupo5.dto.InteressadoInputDTO;
import br.com.devinhouse.grupo5.dto.InteressadoOutputDTO;
import br.com.devinhouse.grupo5.model.Interessado;
import br.com.devinhouse.grupo5.repository.InteressadoRepository;
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
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InteressadoServiceTest {

    @InjectMocks
    private InteressadoService interessadoService;

    @Mock
    private InteressadoRepository interessadoRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void verificarOCadastroDeUmInteressadoComInformacoesValidas(){

        InteressadoInputDTO interessadoInputDTO = new InteressadoInputDTO(
                "Fulano",
                "61201446090",
                LocalDate.parse("2000-12-12"),
                true
        );

        Interessado interessado = new Interessado(
                1L,
                interessadoInputDTO.getNmInteressado(),
                interessadoInputDTO.getNuIdentificacao(),
                interessadoInputDTO.getDtNascimento(),
                interessadoInputDTO.getFlAtivo()
        );

        when(modelMapper.map(interessadoInputDTO, Interessado.class)).thenReturn(interessado);
        when(interessadoRepository.save(interessado)).thenReturn(interessado);

        InteressadoOutputDTO interessadoOutputDTO = interessadoService.cadastrarInteressado(interessadoInputDTO);

        assertAll(
                () -> verify(interessadoRepository).save(interessado)
        );
    }

    @Test
    void verificarOCadastroDeUmInteressadoComCPFInvalido(){

        InteressadoInputDTO interessadoInputDTO = new InteressadoInputDTO(
                "Fulano",
                "12345678910",
                LocalDate.parse("2000-12-12"),
                true
        );

        Throwable exception = catchThrowable(() -> {interessadoService.cadastrarInteressado(interessadoInputDTO);});

        assertThat(exception).isInstanceOf(CpfInvalidoException.class);
    }

    @Test
    void verificarOCadastroDeUmInteressadoComDtNascimentoInvalido(){

        InteressadoInputDTO interessadoInputDTO = new InteressadoInputDTO(
                "Fulano",
                "10736794034",
                LocalDate.parse("2021-12-12"),
                true
        );

        Throwable exception = catchThrowable(() -> {interessadoService.cadastrarInteressado(interessadoInputDTO);});

        assertThat(exception).isInstanceOf(DataDeNascimentoInvalidaException.class);
    }

    @Test
    void deveRetornarUmInteressadoQuandoBuscarPorUmNuIdentificacaoExistente(){

        Interessado interessado = new Interessado(
                1L,
                "Fulano",
                "25191038096",
                LocalDate.parse("2000-12-12"),
                true
        );

        when(interessadoRepository.findByNuIdentificacao(any())).thenReturn(Optional.of(interessado));

        interessadoService.buscarInteressadoPeloNuIdentificacao(interessado.getNuIdentificacao());

        verify(interessadoRepository, times(1)).findByNuIdentificacao(interessado.getNuIdentificacao());
    }

    @Test
    void deveRetornarErroQuandoBuscarPorUmNumeroDeIdentificacaoInexistente(){
        Throwable exception = catchThrowable(() -> interessadoService.buscarInteressadoPeloNuIdentificacao("12345678910"));

        assertThat(exception).isInstanceOf(InteressadoNaoEncontradoException.class);
    }

    @Test
    void deveRetornarUmInteressadoQuandoBuscarPorUmIdExistente(){

        Interessado interessado = new Interessado(
                1L,
                "Fulano",
                "25191038096",
                LocalDate.parse("2000-12-12"),
                true
        );

        when(interessadoRepository.findById(any())).thenReturn(Optional.of(interessado));

        interessadoService.buscarInteressadoPeloId(interessado.getId());

        verify(interessadoRepository, times(1)).findById(interessado.getId());
    }

    @Test
    void deveRetornarErroQuandoBuscarPorUmIdInexistente(){
        Throwable exception = catchThrowable(() -> interessadoService.buscarInteressadoPeloId(1L));

        assertThat(exception).isInstanceOf(InteressadoNaoEncontradoException.class);
    }

}
