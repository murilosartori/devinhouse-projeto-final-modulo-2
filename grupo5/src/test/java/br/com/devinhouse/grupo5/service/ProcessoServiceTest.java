package br.com.devinhouse.grupo5.service;

import br.com.devinhouse.grupo5.domain.exceptions.ProcessoNaoEncontradoException;
import br.com.devinhouse.grupo5.dto.AssuntoOutputDTO;
import br.com.devinhouse.grupo5.dto.InteressadoOutputDTO;
import br.com.devinhouse.grupo5.model.Assunto;
import br.com.devinhouse.grupo5.model.Interessado;
import br.com.devinhouse.grupo5.model.Processo;
import br.com.devinhouse.grupo5.repository.AssuntoRepository;
import br.com.devinhouse.grupo5.repository.InteressadoRepository;
import br.com.devinhouse.grupo5.repository.ProcessoRepository;
import org.aspectj.util.PartialOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessoServiceTest {

	@Mock
	ProcessoRepository processoRepository;

	@InjectMocks
	ProcessoService processoService;

	@Mock
	ModelMapper modelMapper;

	@Mock
	InteressadoService interessadoService;

	@Mock
	AssuntoService assuntoService;

	@Test
	void salvarProcesso() {

	}

	@Test
	void buscarTodosProcessos() {
		ArrayList<Processo> processos = new ArrayList<>();

		when(processoRepository.findAll()).thenReturn(processos);

		processoService.buscarTodosProcessos();

		verify(processoRepository, times(1)).findAll();
	}

	@Test
	void buscarUmProcesso() {
		Processo processo = new Processo();

		when(processoRepository.findById(1L)).thenReturn(Optional.of(processo));

		processoService.buscarUmProcesso(1L);

		verify(processoRepository, times(1)).findById(1L);
	}

	@Test
	void buscarUmProcessoNaoEncontrado() {
		Throwable erro = catchThrowable(() -> processoService.buscarUmProcesso(1L));

		assertThat(erro).isInstanceOf(ProcessoNaoEncontradoException.class);
	}

	@Test
	void buscarUmProcessoPorChave() {
		Processo processo = new Processo();

		when(processoRepository.findByChaveProcesso("SOFT 1/2021")).thenReturn(Optional.of(processo));

		processoService.buscarUmProcessoPorChave("SOFT 1/2021");

		verify(processoRepository, times(1)).findByChaveProcesso("SOFT 1/2021");
	}

	@Test
	void buscarUmProcessoPorChaveNaoEncontrado() {
		Throwable erro = catchThrowable(() -> processoService.buscarUmProcessoPorChave("SOFT 1/2020"));

		assertThat(erro).isInstanceOf(ProcessoNaoEncontradoException.class);
	}

	@Test
	void buscarUmProcessoPorCdInteressado() {
		ArrayList<Processo> processos = new ArrayList<>();
		Processo processo = new Processo();
		processos.add(processo);
		Interessado interessado = new Interessado(1L, "Fulano de Tal", "65701715000", LocalDate.parse("2021-05-26"), true);

		InteressadoOutputDTO interessadoOutputDTO = new InteressadoOutputDTO();

		when(interessadoService.buscarInteressadoPeloId(interessado.getId())).thenReturn(interessadoOutputDTO);
		when(modelMapper.map(interessadoService.buscarInteressadoPeloId(interessado.getId()), Interessado.class))
				.thenReturn(interessado);
		when(processoRepository.findByCdInteressado(interessado)).thenReturn(processos);

		processoService.buscarUmProcessoPorCdInteressado(1L);

		verify(processoRepository, times(1)).findByCdInteressado(interessado);
	}

	@Test
	void buscarUmProcessoPorCdAssunto() {
		ArrayList<Processo> processos = new ArrayList<>();
		Assunto assunto = new Assunto(1L, "Descrição Assunto", LocalDate.parse("2021-05-26"), true);
		AssuntoOutputDTO assuntoOutputDTO = new AssuntoOutputDTO();

		Processo processo = new Processo();

		when(assuntoService.buscarAssuntoPorId(assunto.getId())).thenReturn(assuntoOutputDTO);
		when(modelMapper.map(assuntoService.buscarAssuntoPorId(assunto.getId()), Assunto.class)).thenReturn(assunto);
		when(processoRepository.findByCdAssunto(assunto)).thenReturn(processos);

		processoService.buscarUmProcessoPorCdAssunto(1L);

		verify(processoRepository, times(1)).findByCdAssunto(assunto);
	}

	@Test
	void atualizarProcesso() {
		// Given
		// When
		// Then
	}

	@Test
	void deletarProcesso() {
		// Given
		// When
		// Then
	}
}