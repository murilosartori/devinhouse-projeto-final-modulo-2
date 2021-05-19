package br.com.devinhouse.grupo5.service;

import java.util.Optional;

import br.com.devinhouse.grupo5.exceptions.NuProcessoJaCadastradoException;
import br.com.devinhouse.grupo5.exceptions.ProcessoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devinhouse.grupo5.model.Processo;
import br.com.devinhouse.grupo5.repository.RepositorioDeProcessos;

@Service
public class ServicoDeProcessos {

  @Autowired
  RepositorioDeProcessos processoRepository;

  public Processo salvarProcesso(Processo Processo) {
    if (processoRepository.existsById(Processo.getNuProcesso())) {
      throw new NuProcessoJaCadastradoException("O número de processo informado já encontra-se cadastrado.");
    }
    // TODO: retirar setChaveProcesso e repensar a construção da chave
    Processo.setChaveProcesso();
    processoRepository.save(Processo);
    return Processo;
  }

  public Iterable<Processo> getHistoricoProcesso() {
    return processoRepository.findAll();
  }

  public Processo buscarUmProcesso(Long id) {
    Optional<Processo> processo = processoRepository.findById(id);
    if (processo.isEmpty()) {
      throw new ProcessoNaoEncontradoException("O processo pelo qual buscavas não foi encontrado");
    }
    return processo.get();
  }

  public Processo buscarUmProcessoPorChave(String chaveProcesso) {
    Optional<Processo> processo = processoRepository.findByChaveProcesso(chaveProcesso);
    if (processo.isEmpty()) {
      throw new ProcessoNaoEncontradoException("O processo pelo qual buscavas não foi encontrado");
    }
    return processo.get();
  }

  public void atualizarProcesso(Processo processoAtualizado, Long id) {
    Optional<Processo> processo =  processoRepository.findById(id);
    if (processo.isEmpty()) {
      throw new ProcessoNaoEncontradoException("O processo pelo qual buscavas não foi encontrado");
    }
    /* TODO: Criar um DTO, extrair os dados alteráveis do processoAtualizado por no processo que consta no BD
     */
    processoRepository.save(processoAtualizado);
  }

  public Processo deletarProcesso(Long id) {
    Optional<Processo> processo =  processoRepository.findById(id);
    if (processo.isEmpty()) {
      throw new ProcessoNaoEncontradoException("O processo pelo qual buscavas não foi encontrado");
    }
    processoRepository.deleteById(id);
    return processo.get();
  }
}
