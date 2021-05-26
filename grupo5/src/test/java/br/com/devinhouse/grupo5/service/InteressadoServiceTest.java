package br.com.devinhouse.grupo5.service;

import br.com.devinhouse.grupo5.core.ModelMapperConfig;
import br.com.devinhouse.grupo5.domain.exceptions.InformacaoJaCadastradaException;
import br.com.devinhouse.grupo5.domain.exceptions.InteressadoNaoEncontradoException;
import br.com.devinhouse.grupo5.dto.InteressadoInputDTO;
import br.com.devinhouse.grupo5.dto.InteressadoOutputDTO;
import br.com.devinhouse.grupo5.model.Interessado;
import br.com.devinhouse.grupo5.repository.InteressadoRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InteressadoServiceTest {

    @InjectMocks
    InteressadoService interessadoService;

    @Mock
    InteressadoRepository interessadoRepository;

    @Mock
    ModelMapper modelMapper;

    //
//    @BeforeEach
//    public void setup(){
//interessadoRepository = mock(InteressadoRepository.class);
//        interessadoService = new InteressadoService();
//    }


//    @BeforeEach
//    void setUp() {
//        interessadoService = new InteressadoService(interessadoRepository, modelMapper);
//    }

    @Test
    public void verificarOCadastroDeUmInteressadoComInformacoesValidas(){


//        // given
////        InteressadoInputDTO interessadoDto = new InteressadoInputDTO( "fulano", "12345654321", Date.valueOf("2002-10-31"), true);
////        Interessado interessado = new Interessado(1L, interessadoDto.getNmInteressado(), interessadoDto.getNuIdentificacao(), interessadoDto.getDtNascimento(), interessadoDto.getFlAtivo());
//
//        InteressadoInputDTO interessadoInputDTO = new InteressadoInputDTO(
//                "Fulano",
//                "12478959984",
//                Date.valueOf("2000-12-12"),
//                true
//        );
//
//        Interessado interessado = new Interessado(
//                1L,
//                "Fulano",
//                "12478959984",
//                Date.valueOf("2000-12-12"),
//                true
//        );
//
//        // when
////        when(modelMapper.map(interessadoInputDTO, Interessado.class)).thenReturn(interessado);
////        when(interessadoRepository.save(interessado)).thenReturn(interessado);
//
//        when(modelMapper.map(interessadoInputDTO, Interessado.class)).thenReturn(interessado);
//        when(interessadoRepository.save(interessado)).thenReturn(interessado);
//
//        // then
//        InteressadoOutputDTO interessadoOutputDTO = interessadoService.cadastrarInteressado(interessadoInputDTO);
//
//        assertAll(
//                () -> verify(interessadoRepository).save(interessado)
//              //  () -> verify(interessadoRepository, times(1)).save(interessado)
//        );

//        //given
        InteressadoInputDTO interessadoInputDTO = new InteressadoInputDTO(
                "Fulano",
                "12345654321",
                Date.valueOf("2000-12-12"),
                true
        );

        Interessado interessado = new Interessado(
                1L,
                "Fulano",
                "12345654321",
                Date.valueOf("2000-12-12"),
                true
        );

        //when
        when(modelMapper.map(interessadoInputDTO, Interessado.class)).thenReturn(interessado);
        when(interessadoRepository.save(interessado)).thenReturn(interessado);

        InteressadoOutputDTO interessadoOutputDTO = interessadoService.cadastrarInteressado(interessadoInputDTO);

        //then
       assertAll(
               () -> verify(interessadoRepository).save(interessado)
        );



//        assertThat(catchThrowable(() -> {
//            interessadoRepository.findByNuIdentificacao(interessado.getNuIdentificacao());

//        })).isInstanceOf(InformacaoJaCadastradaException.class);


//        InteressadoInputDTO interessadoInputDTO = new
//                InteressadoInputDTO("Pedro Silva", "12478959984",
//                Date.from(Instant.now()), true);
//
//        Interessado interessado = new Interessado(1L,
//                interessadoInputDTO.getNmInteressado(),
//                interessadoInputDTO.getNuIdentificacao(), interessadoInputDTO.getDtNascimento(), interessadoInputDTO.getFlAtivo()
//        );
//
//        InteressadoOutputDTO interessadoOutputDTO = new InteressadoOutputDTO(1L,
//                interessado.getNmInteressado(), interessado.getNuIdentificacao(),
//                interessado.getDtNascimento(), interessado.getFlAtivo());
//
//        when(modelMapper.map(interessadoInputDTO, Interessado.class)).thenReturn(interessado);
//
//        when(modelMapper.map(interessado, InteressadoOutputDTO.class)).thenReturn(interessadoOutputDTO);
//
//        InteressadoOutputDTO outputDTO = interessadoService.cadastrarInteressado(interessadoInputDTO);
//
//        AssertionsForClassTypes.assertThat(outputDTO).isInstanceOf(InteressadoOutputDTO.class);
    }

    @Test
    public void DEVE_RETORNAR_ERRO_QUANDO_BUSCAR_POR_UM_INTERESSADO_PELO_NUIDENTIFICACAO_E_O_MESMO_NAO_ESTIVER_CADASTRADO(){
        Throwable exception = catchThrowable(() -> interessadoService.buscarInteressadoPeloNuIdentificacao("11122233355"));

        assertThat(exception).isInstanceOf(InteressadoNaoEncontradoException.class);
    }

    @Test
    public void DEVE_RETORNAR_ERRO_QUANDO_BUSCAR_POR_UM_INTERESSADO_PELO_ID_E_O_ID_NAO_ESTIVER_CADASTRADO(){
        Throwable exception = catchThrowable(() -> {
            interessadoService.buscarInteressadoPeloId(99L);
        });

        assertThat(exception).isInstanceOf(InteressadoNaoEncontradoException.class);
    }

}
