package br.com.devinhouse.grupo5.service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.devinhouse.grupo5.domain.exceptions.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devinhouse.grupo5.dto.AssuntoOutputDTO;
import br.com.devinhouse.grupo5.dto.InteressadoOutputDTO;
import br.com.devinhouse.grupo5.dto.ProcessoInputDTO;
import br.com.devinhouse.grupo5.dto.ProcessoOutputDTO;
import br.com.devinhouse.grupo5.model.Assunto;
import br.com.devinhouse.grupo5.model.Interessado;
import br.com.devinhouse.grupo5.model.Processo;
import br.com.devinhouse.grupo5.repository.ProcessoRepository;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class ProcessoService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	InteressadoService interessadoService;

	@Autowired
	AssuntoService assuntoService;

	@Autowired
  ProcessoRepository processoRepository;

	public ProcessoOutputDTO salvarProcesso(ProcessoInputDTO processoInputDTO) {
		var processo = toProcesso(processoInputDTO);

		Boolean existNuProcesso = processoRepository.existsByNuProcesso(processo.getNuProcesso());
		if (TRUE.equals(existNuProcesso)) {
			throw new NuProcessoJaCadastradoException();
		}

		Boolean existChaveProcesso = processoRepository.existsByChaveProcesso(processo.getChaveProcesso());
		if (TRUE.equals(existChaveProcesso)) {
			throw new NuProcessoJaCadastradoException(processo.getChaveProcesso());
		}

		// 3 - Não poderá ser cadastrado um novo processo com interessados inativos;
		// 4 - Não poderá ser cadastrado um novo processo com interessados inexistentes no sistema;
		InteressadoOutputDTO interessadoOut = interessadoService.buscarInteressadoPeloId(processoInputDTO.getCdInteressado());
		if (interessadoOut != null) {
			if (FALSE.equals(interessadoOut.getFlAtivo())) {
				throw new InativoException();
			}
			processo.setCdInteressado(modelMapper.map(interessadoOut, Interessado.class));
		} else {
			throw new InteressadoNaoEncontradoException();
		}

		// 5 - Não poderá ser cadastrado um novo processo com assuntos inativos;
		// 6 - Não poderá ser cadastrado um novo processo com assuntos inexistentes no sistema;
		AssuntoOutputDTO assuntoOut = assuntoService.buscarAssuntoPorId(processoInputDTO.getCdAssunto());
		if (assuntoOut != null) {
			if (FALSE.equals(assuntoOut.getFlAtivo())) {
				throw new InativoException("O assunto informado encontra-se inativo no momento.");
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

	public List<ProcessoOutputDTO> buscarUmProcessoPorCdInteressado(Long cdInteressado) {
		var interessado = modelMapper.map(interessadoService.buscarInteressadoPeloId(cdInteressado), Interessado.class);
		List<Processo> processos = processoRepository.findByCdInteressado(interessado);
//		if (processos.isEmpty()) {
//			throw new ProcessoNaoEncontradoException("interessado", cdInteressado + "");
//		}
		return toDTO(processos);
	}

	public List<ProcessoOutputDTO> buscarUmProcessoPorCdAssunto(Long cdAssunto) {
		var assunto = modelMapper.map(assuntoService.buscarAssuntoPorId(cdAssunto), Assunto.class);
		List<Processo> processos = processoRepository.findByCdAssunto(assunto);
//		if (processos.isEmpty()) {
//			throw new ProcessoNaoEncontradoException("assunto", cdAssunto + "");
//		}
		return toDTO(processos);
	}

	public void atualizarProcesso(ProcessoInputDTO processoInputDTO, Long id) {
		var processoIndicado = processoRepository.findById(id)
				.orElseThrow(() -> new ProcessoNaoEncontradoException(id));
		var processoAtualizado = toProcesso(processoInputDTO);

		if(!processoAtualizado.getNuProcesso().equals(processoIndicado.getNuProcesso())) {

			Boolean existNuProcesso = processoRepository.existsByNuProcesso(processoAtualizado.getNuProcesso());
			if (TRUE.equals(existNuProcesso)) {
				throw new NuProcessoJaCadastradoException();
			}
		}

		if(!processoAtualizado.getChaveProcesso().equals(processoIndicado.getChaveProcesso())) {

			Boolean existChaveProcesso = processoRepository.existsByChaveProcesso(processoAtualizado.getChaveProcesso());
			if (TRUE.equals(existChaveProcesso)) {
				throw new NuProcessoJaCadastradoException(processoAtualizado.getChaveProcesso());
			}
		}

		// 3 - Não poderá ser cadastrado um novo processo com interessados inativos;
		// 4 - Não poderá ser cadastrado um novo processo com interessados inexistentes no sistema;
		InteressadoOutputDTO interessadoOut = interessadoService.buscarInteressadoPeloId(processoInputDTO.getCdInteressado());
		if (interessadoOut != null) {
			if (FALSE.equals(interessadoOut.getFlAtivo())) {
				throw new InativoException();
			}
			processoAtualizado.setCdInteressado(modelMapper.map(interessadoOut, Interessado.class));
		} else {
			throw new InteressadoNaoEncontradoException();
		}

		// 5 - Não poderá ser cadastrado um novo processo com assuntos inativos;
		// 6 - Não poderá ser cadastrado um novo processo com assuntos inexistentes no sistema;
		AssuntoOutputDTO assuntoOut = assuntoService.buscarAssuntoPorId(processoInputDTO.getCdAssunto());
		if (assuntoOut != null) {
			if (FALSE.equals(assuntoOut.getFlAtivo())) {
				throw new InativoException("O assunto informado encontra-se inativo no momento.");
			}
			processoAtualizado.setCdAssunto(modelMapper.map(assuntoOut, Assunto.class));
		} else {
			throw new AssuntoNaoEncontradoException();
		}
		BeanUtils.copyProperties(processoAtualizado, processoIndicado, "id");
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
