package br.com.devinhouse.grupo5.service;

import br.com.devinhouse.grupo5.domain.exceptions.InteressadoNaoEncontradoException;
import br.com.devinhouse.grupo5.dto.AssuntoInputDTO;
import br.com.devinhouse.grupo5.dto.AssuntoOutputDTO;
import br.com.devinhouse.grupo5.dto.InteressadoInputDTO;
import br.com.devinhouse.grupo5.dto.InteressadoOutputDTO;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@SpringBootTest
public class InteressadoServiceTest {

    @Autowired
    InteressadoService interessadoService;

    @Test
    public void DEVE_RETORNAR_UM_INTERESSADOOUTPUTDTO_QUANDO_DO_CADASTRO_DUM_INTERESSADO(){
        InteressadoInputDTO interessadoInputDTO = new
                InteressadoInputDTO("Pedro Silva", "11122233344", Date.from(Instant.now()), true);

        InteressadoOutputDTO interessadoOutputDTO = interessadoService.cadastrarInteressado(interessadoInputDTO);

        AssertionsForClassTypes.assertThat(interessadoOutputDTO).isInstanceOf(InteressadoOutputDTO.class);
    }

    @Test
    public void DEVE_RETORNAR_ERRO_QUANDO_BUSCAR_POR_UM_INTERESSADO_PELO_NUIDENTIFICACAO_E_O_MESMO_NAO_ESTIVER_CADASTRADO(){
        Throwable exception = catchThrowable(() -> interessadoService.buscarInteressadoPeloNuIdentificacao("11122233344"));

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
