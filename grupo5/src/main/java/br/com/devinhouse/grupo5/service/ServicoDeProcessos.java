package br.com.devinhouse.grupo5.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.devinhouse.model.Processo;
import br.com.devinhouse.repository.ProcessoRepository;

@Service
public class ServicoDeProcessos {
  
  @Autowired
  ProcessoRepository ProcessoRepository;

  public ResponseEntity<String> saveProcesso(Processo Processo) {
    HttpHeaders header = new HttpHeaders();
    if (ProcessoRepository.existsById(Processo.getNuProcesso())) {
      header.add("Mensagem:", "Numero de processo ja existe");
      return new ResponseEntity<>(ServiceStatus.JSON(409, "Numero de processo ja existe."), header, HttpStatus.CONFLICT);
    }
    Processo.setChaveProcesso();
    ProcessoRepository.save(Processo);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  public Iterable<Processo> getProcessoHistory() {
    return ProcessoRepository.findAll();
  }

  public ResponseEntity<?> buscaUmProcesso(Long id) {
    HttpHeaders header = new HttpHeaders();
    if (!ProcessoRepository.findById(id).isPresent()) {
      header.add("Mensagem:", "Processo nao encontrado");
      return new ResponseEntity<String>(ServiceStatus.JSON(), header, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Optional<Processo>>(ProcessoRepository.findById(id), header, HttpStatus.OK);
  }

  public ResponseEntity<?> buscaUmProcessoPorChave(String chaveProcesso) {
    HttpHeaders header = new HttpHeaders();
    if (!ProcessoRepository.findByChaveProcesso(chaveProcesso).isPresent()) {
      header.add("Mensagem:", "Processo nao encontrado");
      return new ResponseEntity<>(ServiceStatus.JSON(), header, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(ProcessoRepository.findByChaveProcesso(chaveProcesso), header, HttpStatus.OK);
  }

  public ResponseEntity<?> atualizaProcesso(Processo processoAtualizado, Long id) {
    HttpHeaders header = new HttpHeaders();
    if (ProcessoRepository.existsById(id)) {
      processoAtualizado.setChaveProcesso();
      ProcessoRepository.save(processoAtualizado);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    header.add("Mensagem:", "Processo nao encontrado");
    return new ResponseEntity<>(ServiceStatus.JSON(), header, HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<?> deletaProcesso(Long id) {
    HttpHeaders header = new HttpHeaders();
    if (ProcessoRepository.existsById(id)) {
      ProcessoRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    header.add("Mensagem:", "Processo nao encontrado");
    return new ResponseEntity<>(ServiceStatus.JSON(), header, HttpStatus.NOT_FOUND);
  }
}
