package br.com.devinhouse.grupo5.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devinhouse.grupo5.domain.exceptions.AssuntoNaoEncontradoException;
import br.com.devinhouse.grupo5.domain.exceptions.InteressadoNaoEncontradoException;
import br.com.devinhouse.grupo5.domain.exceptions.NuProcessoJaCadastradoException;
import br.com.devinhouse.grupo5.domain.exceptions.ProcessoNaoEncontradoException;
import br.com.devinhouse.grupo5.dto.AssuntoOutputDTO;
import br.com.devinhouse.grupo5.dto.InteressadoOutputDTO;
import br.com.devinhouse.grupo5.dto.ProcessoInputDTO;
import br.com.devinhouse.grupo5.dto.ProcessoOutputDTO;
import br.com.devinhouse.grupo5.model.Assunto;
import br.com.devinhouse.grupo5.model.Interessado;
import br.com.devinhouse.grupo5.model.Processo;
import br.com.devinhouse.grupo5.repository.RepositorioDeProcessos;

@Service
public class ServicoDeProcessos {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	InteressadoService interessadoService;
	
	@Autowired
	AssuntoService assuntoService;
	
	@Autowired
	RepositorioDeProcessos processoRepository;

	public ProcessoOutputDTO salvarProcesso(ProcessoInputDTO processoInputDTO) {
		var processo = toProcesso(processoInputDTO);

		// 1 - Não poderá ser cadastrado um novo processo com um id já existente;
		Boolean existNuProcesso = processoRepository.existsByNuProcesso(processo.getNuProcesso());
		if (Boolean.TRUE.equals(existNuProcesso)) {
			throw new NuProcessoJaCadastradoException();
		}
		
		// 2 - Não poderá ser cadastrado um novo processo com uma chave de processo já existente;
		Boolean existChaveProcesso = processoRepository.existsByChaveProcesso(processo.getChaveProcesso());
		if (Boolean.TRUE.equals(existChaveProcesso)) {
			throw new NuProcessoJaCadastradoException(processo.getChaveProcesso());
		}
		
		// 3 - Não poderá ser cadastrado um novo processo com interessados inativos;
		// 4 - Não poderá ser cadastrado um novo processo com interessados inexistentes no sistema;
		InteressadoOutputDTO interessadoOut = interessadoService.buscarInteressadoPeloId(processoInputDTO.getCdInteressado());
		if (interessadoOut != null) {
			if (Boolean.FALSE.equals(interessadoOut.getFlAtivo())) {
				throw new InteressadoNaoEncontradoException("O interessado informado encontra-se inativo no momento.");
			}
			processo.setCdInteressado(modelMapper.map(interessadoOut, Interessado.class));
		} else {
			throw new InteressadoNaoEncontradoException();
		}
		
		// 5 - Não poderá ser cadastrado um novo processo com assuntos inativos;
		// 6 - Não poderá ser cadastrado um novo processo com assuntos inexistentes no sistema;
		AssuntoOutputDTO assuntoOut = assuntoService.buscarAssuntoPorId(processoInputDTO.getCdAssunto());
		if (assuntoOut != null) {
			if (Boolean.FALSE.equals(assuntoOut.getFlAtivo())) {
				throw new AssuntoNaoEncontradoException("O assunto informado encontra-se inativo no momento.");
			}
			processo.setCdAssunto(modelMapper.map(assuntoOut, Assunto.class));
		} else {
			throw new AssuntoNaoEncontradoException();
		}
		return toDTO(processoRepository.save(processo));
	}

	public List<ProcessoOutputDTO> buscarTodosProcessos() {
		return toDTO(processoRepository.findAll());
	}

	public ProcessoOutputDTO buscarUmProcesso(Long id) {
		var processo = processoRepository.findById(id).orElseThrow(() -> new ProcessoNaoEncontradoException(id));
		return toDTO(processo);
	}

	public ProcessoOutputDTO buscarUmProcessoPorChave(String chaveProcesso) {
		var processo = processoRepository.findByChaveProcesso(chaveProcesso)
				.orElseThrow(() -> new ProcessoNaoEncontradoException(chaveProcesso));
		return toDTO(processo);
	}

	public ProcessoOutputDTO buscarUmProcessoPorCdInteressado(Long cdInteressado) {
		Interessado interessado = modelMapper.map(interessadoService.buscarInteressadoPeloId(cdInteressado), Interessado.class);
		var processo = processoRepository.findByCdInteressado(interessado)
				.orElseThrow(() -> new ProcessoNaoEncontradoException(cdInteressado));
		return toDTO(processo);
	}
	
	public ProcessoOutputDTO buscarUmProcessoPorCdAssunto(Long cdAssunto) {
		Assunto assunto = modelMapper.map(assuntoService.buscarAssuntoPorId(cdAssunto), Assunto.class);
		var processo = processoRepository.findByCdAssunto(assunto)
				.orElseThrow(() -> new ProcessoNaoEncontradoException(cdAssunto));
		return toDTO(processo);
	}

	public void atualizarProcesso(ProcessoInputDTO processoInputDTO, Long id) {
		var processoIndicado = processoRepository.findById(id)
				.orElseThrow(() -> new ProcessoNaoEncontradoException(id));
		var processoAtualizado = toProcesso(processoInputDTO);
		BeanUtils.copyProperties(processoIndicado, processoAtualizado, "id");
		// TODO: conferir se há alguma informação que deverá ser ignorada além de id
		processoRepository.save(processoIndicado);
	}

	public ProcessoOutputDTO deletarProcesso(Long id) {
		var processo = processoRepository.findById(id).orElseThrow(() -> new ProcessoNaoEncontradoException(id));
		processoRepository.deleteById(id);
		return toDTO(processo);
	}

	private Processo toProcesso(ProcessoInputDTO processoInputDTO) {
		return modelMapper.map(processoInputDTO, Processo.class);
	}

	private ProcessoOutputDTO toDTO(Processo processo) {
		return modelMapper.map(processo, ProcessoOutputDTO.class);
	}

	private List<ProcessoOutputDTO> toDTO(List<Processo> processos) {
		return processos.stream().map(this::toDTO).collect(Collectors.toList());
	}

}
