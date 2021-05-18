package br.com.devinhouse.grupo5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.devinhouse.model.Processo;
import br.com.devinhouse.service.ProcessoService;

@RestController
public class ProcessoController {

  @Autowired
  ProcessoService service;

  @PostMapping(path = "v1" + "/processo", headers = "api-version=2021-04-21")
  public ResponseEntity<String> criaProcesso(@RequestBody Processo Processo) {
    return service.saveProcesso(Processo);
  }

  @GetMapping(path = "v1" + "/processos", headers = "api-version=2021-04-21")
  public Iterable<Processo> listaProcessos() {
    return service.getProcessoHistory();
  }

  @GetMapping(path = "v1" + "/processo/{id}", headers = "api-version=2021-04-21")
  public ResponseEntity<?> buscaUmProcesso(@PathVariable("id") Long id) {
    return service.buscaUmProcesso(id);
  }

  @GetMapping(path = "v1" + "/processo", headers = "api-version=2021-04-21")
  public ResponseEntity<?> viewProcessoById(@RequestParam("chaveProcesso") String chaveProcesso) {
    return service.buscaUmProcessoPorChave(chaveProcesso);
  }

  @PutMapping(path = "v1" + "/processo/{id}", headers = "api-version=2021-04-21")
  public ResponseEntity<?> atualizaProcesso(@RequestBody Processo processoAtualizado,
      @PathVariable("id") Long id) {
    return service.atualizaProcesso(processoAtualizado, id);
  }

  @DeleteMapping(path = "v1" + "/processo/{id}", headers = "api-version=2021-04-21")
  public ResponseEntity<?> deletaProcesso(@PathVariable("id") Long id) {
    return service.deletaProcesso(id);
  }

}
