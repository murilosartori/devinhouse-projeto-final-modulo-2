package br.com.devinhouse.grupo5.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devinhouse.grupo5.domain.exceptions.NuProcessoJaCadastradoException;
import br.com.devinhouse.grupo5.domain.exceptions.ProcessoNaoEncontradoException;
import br.com.devinhouse.grupo5.dto.ProcessoInputDTO;
import br.com.devinhouse.grupo5.dto.ProcessoOutputDTO;
import br.com.devinhouse.grupo5.model.Processo;
import br.com.devinhouse.grupo5.repository.RepositorioDeProcessos;

@Service
public class ServicoDeProcessos {

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  RepositorioDeProcessos processoRepository;

  public ProcessoOutputDTO salvarProcesso(ProcessoInputDTO processoInputDTO) {
    var processo = toProcesso(processoInputDTO);
    Boolean b = processoRepository.existsByNuProcesso(processo.getNuProcesso());
    // 1 - Não poderá ser cadastrado um novo processo com um id já existente;
    if (Boolean.TRUE.equals(b)) {
      throw new NuProcessoJaCadastradoException(processo.getChaveProcesso());
    }
    //TODO: 2 - Não poderá ser cadastrado um novo processo com uma chave de processo já existente;
    //TODO: 3 - Não poderá ser cadastrado um novo processo com interessados inativos;
    //TODO: 4 - Não poderá ser cadastrado um novo processo com assuntos inativos;
    //TODO: 5 - Não poderá ser cadastrado um novo processo com interessados inesistentes no sistema;
    //TODO: 6 - Não poderá ser cadastrado um novo processo com assuntos inesistentes no sistema;
    //TODO: 7 - Não poderá ser cadastrado um novo interessado com um id já existente;
    //TODO: 8 - Não poderá ser cadastrado um novo interessado com um mesmo documento de indentificação;
    //TODO: 9 - Não poderá ser cadastrado um novo interessado com um documento de identificação inválido;
    return toDTO(processoRepository.save(processo));
  }

  public List<ProcessoOutputDTO> buscarTodosProcessos() {
    return toDTO(processoRepository.findAll());
  }

  public ProcessoOutputDTO buscarUmProcesso(Long id) {
    var processo = processoRepository.findById(id)
      .orElseThrow(() -> new ProcessoNaoEncontradoException(id));
    return toDTO(processo);
  }

  public ProcessoOutputDTO buscarUmProcessoPorChave(String chaveProcesso) {
    var processo = processoRepository.findByChaveProcesso(chaveProcesso)
    .orElseThrow(() -> new ProcessoNaoEncontradoException(chaveProcesso));
    return toDTO(processo);
  }

  public void atualizarProcesso(ProcessoInputDTO processoInputDTO, Long id) {
    var processoIndicado = processoRepository.findById(id)
      .orElseThrow(() -> new ProcessoNaoEncontradoException(id));
    var processoAtualizado = toProcesso(processoInputDTO);
    BeanUtils.copyProperties(processoIndicado, processoAtualizado, "id");
    //TODO: conferir se há alguma informação que deverá ser ignorada além de id
    processoRepository.save(processoIndicado);
  }

  public ProcessoOutputDTO deletarProcesso(Long id) {
    var processo = processoRepository.findById(id)
      .orElseThrow(() -> new ProcessoNaoEncontradoException(id));
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
