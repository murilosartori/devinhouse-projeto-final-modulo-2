package br.com.devinhouse.grupo5.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.devinhouse.grupo5.model.Processo;
import br.com.devinhouse.grupo5.repository.RepositorioDeProcessos;

@Service
public class ServicoDeProcessos {

  @Autowired
  RepositorioDeProcessos processoRepository;

  public Processo saveProcesso(Processo Processo) {
    if (processoRepository.existsById(Processo.getNuProcesso())) {
      // TODO: lançar um erro de negócio como throws CPFJaExisteException...
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
      // TODO: lançar um erro de negócio como throws ProcessoNaoEncontradoException...
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
      // TODO: lançar um erro de negócio como throws ProcessoNaoEncontradoException...
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
      // TODO: lançar um erro de negócio como throws ProcessoNaoEncontradoException...
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
      // TODO: lançar um erro de negócio como throws ProcessoNaoEncontradoException...
      // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      // header.add("Mensagem:", "Processo nao encontrado");
    }
    processoRepository.deleteById(id);
    return processo.get();
  }
}
