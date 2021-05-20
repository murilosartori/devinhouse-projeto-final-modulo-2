package br.com.devinhouse.grupo5.service;

import java.util.Optional;


import br.com.devinhouse.grupo5.domain.exceptions.NuProcessoJaCadastradoException;
import br.com.devinhouse.grupo5.domain.exceptions.ProcessoNaoEncontradoException;
import br.com.devinhouse.grupo5.dto.ProcessoInputDTO;
import br.com.devinhouse.grupo5.dto.ProcessoOutputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devinhouse.grupo5.model.Processo;
import br.com.devinhouse.grupo5.repository.RepositorioDeProcessos;

@Service
public class ServicoDeProcessos {

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  RepositorioDeProcessos processoRepository;

  public ProcessoOutputDTO salvarProcesso(ProcessoInputDTO processo) {
    if (processoRepository.findByNuProcesso(processo.getNuProcesso()).isPresent()) {
      throw new NuProcessoJaCadastradoException();
    }
    processoRepository.save(modelMapper.map(processo, Processo.class));
    return modelMapper.map(processo, ProcessoOutputDTO.class);
  }

  public Iterable<Processo> getHistoricoProcesso() {
    return processoRepository.findAll();
  }


  public ProcessoOutputDTO buscarUmProcesso(Long id) {

    Optional<Processo> processo = processoRepository.findById(id);
    if (processo.isEmpty()) {
      throw new ProcessoNaoEncontradoException();
    }
    return modelMapper.map(processo.get(), ProcessoOutputDTO.class);
  }

  public ProcessoOutputDTO buscarUmProcessoPorChave(String chaveProcesso) {

    Optional<Processo> processo = processoRepository.findByChaveProcesso(chaveProcesso);
    if (processo.isEmpty()) {
      throw new ProcessoNaoEncontradoException();
    }
    return modelMapper.map(processo.get(), ProcessoOutputDTO.class);
  }

  public void atualizarProcesso(Processo processoAtualizado, Long id) {
    Optional<Processo> processo =  processoRepository.findById(id);
    if (processo.isEmpty()) {
      throw new ProcessoNaoEncontradoException();
    }
    /* TODO: Criar um DTO, extrair os dados alteráveis do processoAtualizado por no processo que consta no BD
     */
    processoRepository.save(processoAtualizado);
  }

  public ProcessoOutputDTO deletarProcesso(Long id) {
    Optional<Processo> processo =  processoRepository.findById(id);
    if (processo.isEmpty()) {
      throw new ProcessoNaoEncontradoException();
    }
    processoRepository.deleteById(id);
    return modelMapper.map(processo.get(), ProcessoOutputDTO.class);
  }

}
