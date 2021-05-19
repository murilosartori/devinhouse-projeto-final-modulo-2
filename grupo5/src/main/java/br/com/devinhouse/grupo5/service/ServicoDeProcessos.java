package br.com.devinhouse.grupo5.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devinhouse.grupo5.domain.exceptions.CpfJaExistenteException;
import br.com.devinhouse.grupo5.domain.exceptions.ProcessoNaoEncontradoException;
import br.com.devinhouse.grupo5.model.Processo;
import br.com.devinhouse.grupo5.repository.RepositorioDeProcessos;

@Service
public class ServicoDeProcessos {

  @Autowired
  RepositorioDeProcessos processoRepository;

  public Processo saveProcesso(Processo Processo) {
    if (processoRepository.existsById(Processo.getNuProcesso())) {
      throw new CpfJaExistenteException("Numero de processo ja existe");
      // ANTIGO: HttpHeaders header = new HttpHeaders();
      // header.add("Mensagem:", "Numero de processo ja existe");
      // return new ResponseEntity<>(ServiceStatus.JSON(409, "Numero de processo ja
      // existe."), header, HttpStatus.CONFLICT);
    }
    // TODO: retirar setChaveProcesso e repensar a construção da chave
    Processo.setChaveProcesso();
    processoRepository.save(Processo);
    return Processo;
  }

  public Iterable<Processo> getProcessoHistory() {
    return processoRepository.findAll();
  }

  public Processo buscaUmProcesso(Long id) {
    Optional<Processo> processo = processoRepository.findById(id);
    if (processo.isEmpty()) {
      throw new ProcessoNaoEncontradoException("Processo nao encontrado");
      // ANTIGO: HttpHeaders header = new HttpHeaders();
      // header.add("Mensagem:", "Processo nao encontrado");
      // return new ResponseEntity<String>(ServiceStatus.JSON(), header,
      // HttpStatus.NOT_FOUND);
    }
    return processo.get();
  }

  public Processo buscaUmProcessoPorChave(String chaveProcesso) {
    Optional<Processo> processo = processoRepository.findByChaveProcesso(chaveProcesso);
    if (processo.isEmpty()) {
      throw new ProcessoNaoEncontradoException("Processo nao encontrado");
      // ANTIGO: HttpHeaders header = new HttpHeaders();
      // header.add("Mensagem:", "Processo nao encontrado");
      // return new ResponseEntity<>(ServiceStatus.JSON(), header,
      // HttpStatus.NOT_FOUND);
    }
    return processo.get();
  }

  public void atualizaProcesso(Processo processoAtualizado, Long id) {
    Optional<Processo> processo =  processoRepository.findById(id);
    if (processo.isEmpty()) {
      throw new ProcessoNaoEncontradoException("Processo nao encontrado");
      // HttpHeaders header = new HttpHeaders();
      // processoAtualizado.setChaveProcesso();
      // processoRepository.save(processoAtualizado);
      // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /* TODO: Criar um DTO, extrair os dados alteráveis do processoAtualizado por no processo que consta no BD
     */
    processoRepository.save(processoAtualizado);
  }

  public Processo deletaProcesso(Long id) {
    Optional<Processo> processo =  processoRepository.findById(id);
    if (processo.isEmpty()) {
      throw new ProcessoNaoEncontradoException("Processo nao encontrado");
      // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      // header.add("Mensagem:", "Processo nao encontrado");
    }
    processoRepository.deleteById(id);
    return processo.get();
  }
}
