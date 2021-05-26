package br.com.devinhouse.grupo5.service;

import br.com.devinhouse.grupo5.core.ModelMapperConfig;
import br.com.devinhouse.grupo5.domain.exceptions.InteressadoNaoEncontradoException;
import br.com.devinhouse.grupo5.dto.InteressadoInputDTO;
import br.com.devinhouse.grupo5.dto.InteressadoOutputDTO;
import br.com.devinhouse.grupo5.model.Interessado;
import br.com.devinhouse.grupo5.repository.InteressadoRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
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

    @Mock
    ModelMapperConfig modelMapperConfig;

    //
//    @BeforeEach
//    public void setup(){
//interessadoRepository = mock(InteressadoRepository.class);
//        interessadoService = new InteressadoService();
//    }

    @Test
    public void DEVE_RETORNAR_UM_INTERESSADOOUTPUTDTO_QUANDO_DO_CADASTRO_DUM_INTERESSADO(){

        InteressadoInputDTO interessadoInputDTO = new
                InteressadoInputDTO("Pedro Silva", "12478959984",
                Date.from(Instant.now()), true);

        Interessado interessado = new Interessado(1L,
                interessadoInputDTO.getNmInteressado(),
                interessadoInputDTO.getNuIdentificacao(), interessadoInputDTO.getDtNascimento(), interessadoInputDTO.getFlAtivo()
        );

        InteressadoOutputDTO interessadoOutputDTO = new InteressadoOutputDTO(1L,
                interessado.getNmInteressado(), interessado.getNuIdentificacao(),
                interessado.getDtNascimento(), interessado.getFlAtivo());

        when(modelMapper.map(interessadoInputDTO, Interessado.class)).thenReturn(interessado);

        when(modelMapper.map(interessado, InteressadoOutputDTO.class)).thenReturn(interessadoOutputDTO);

        InteressadoOutputDTO outputDTO = interessadoService.cadastrarInteressado(interessadoInputDTO);

        AssertionsForClassTypes.assertThat(outputDTO).isInstanceOf(InteressadoOutputDTO.class);
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
