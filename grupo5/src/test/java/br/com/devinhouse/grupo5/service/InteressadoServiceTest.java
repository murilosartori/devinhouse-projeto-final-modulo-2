package br.com.devinhouse.grupo5.service;

import br.com.devinhouse.grupo5.domain.exceptions.InteressadoNaoEncontradoException;
import br.com.devinhouse.grupo5.dto.AssuntoInputDTO;
import br.com.devinhouse.grupo5.dto.AssuntoOutputDTO;
import br.com.devinhouse.grupo5.dto.InteressadoInputDTO;
import br.com.devinhouse.grupo5.dto.InteressadoOutputDTO;
import br.com.devinhouse.grupo5.model.Assunto;
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
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InteressadoServiceTest {

    @InjectMocks
    private InteressadoService interessadoService;

    @Mock
    private InteressadoRepository interessadoRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        interessadoRepository.deleteAll();
    }

    @Test
    public void verificarOCadastroDeUmInteressadoComInformacoesValidas(){

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

        when(modelMapper.map(interessadoInputDTO, Interessado.class)).thenReturn(interessado);
        when(interessadoRepository.save(interessado)).thenReturn(interessado);

        InteressadoOutputDTO interessadoOutputDTO = interessadoService.cadastrarInteressado(interessadoInputDTO);

        assertAll(
                () -> verify(interessadoRepository).save(interessado)
        );
    }

    @Test
    public void deveRetornarUmInteressadoQuandoBuscarPorUmNuIdentificacaoExistente(){
        ArrayList<Interessado> interessados = new ArrayList<>();

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

        InteressadoOutputDTO interessadoOutputDTO = new InteressadoOutputDTO(
                interessado.getId(),
                interessado.getNmInteressado(),
                interessado.getNuIdentificacao(),
                interessado.getDtNascimento(),
                interessado.getFlAtivo()
        );

        when(modelMapper.map(interessadoInputDTO, Interessado.class)).thenReturn(interessado);
        when(interessadoRepository.save(interessado)).then((Interessado) -> {
            interessados.add(interessado);
            return interessado;
        });
        when(modelMapper.map(interessado, InteressadoOutputDTO.class)).thenReturn(interessadoOutputDTO);
        when(interessadoRepository.findByNuIdentificacao(interessado.getNuIdentificacao())).then((Interessado) -> {
            Stream<Interessado> interessadoStream = interessados.stream().filter(value -> value.getNuIdentificacao().equals(interessado.getNuIdentificacao()));
            return interessadoStream.findFirst();
        });

        interessadoService.cadastrarInteressado(interessadoInputDTO);

        interessadoService.buscarInteressadoPeloNuIdentificacao(interessado.getNuIdentificacao());

        verify(interessadoRepository, times(2)).findByNuIdentificacao(interessado.getNuIdentificacao());
    }

    @Test
    public void deveRetornarErroQuandoBuscarPorUmNumeroDeIdentificacaoInexistente(){
        Throwable exception = catchThrowable(() -> interessadoService.buscarInteressadoPeloNuIdentificacao("12345678910"));

        assertThat(exception).isInstanceOf(InteressadoNaoEncontradoException.class);
    }

    @Test
    public void deveRetornarUmInteressadoQuandoBuscarPorUmIdExistente(){
        ArrayList<Interessado> interessados = new ArrayList<>();

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

        InteressadoOutputDTO interessadoOutputDTO = new InteressadoOutputDTO(
                interessado.getId(),
                interessado.getNmInteressado(),
                interessado.getNuIdentificacao(),
                interessado.getDtNascimento(),
                interessado.getFlAtivo()
        );

        when(modelMapper.map(interessadoInputDTO, Interessado.class)).thenReturn(interessado);
        when(interessadoRepository.save(interessado)).then((Interessado) -> {
            interessados.add(interessado);
            return interessado;
        });
        when(modelMapper.map(interessado, InteressadoOutputDTO.class)).thenReturn(interessadoOutputDTO);
        when(interessadoRepository.findById(interessado.getId())).then((Interessado) -> {
            Stream<Interessado> interessadoStream = interessados.stream().filter(value -> value.getId().equals(interessado.getId()));
            return interessadoStream.findFirst();
        });

        interessadoService.cadastrarInteressado(interessadoInputDTO);

        interessadoService.buscarInteressadoPeloId(interessado.getId());

        verify(interessadoRepository, times(1)).findById(interessado.getId());
    }

    @Test
    public void deveRetornarErroQuandoBuscarPorUmIdInexistente(){
        Throwable exception = catchThrowable(() -> interessadoService.buscarInteressadoPeloId(1L));

        assertThat(exception).isInstanceOf(InteressadoNaoEncontradoException.class);
    }

}
