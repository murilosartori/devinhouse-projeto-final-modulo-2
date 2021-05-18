package br.com.devinhouse.grupo5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.devinhouse.grupo5.model.Processo;
import br.com.devinhouse.grupo5.service.ServicoDeProcessos;

@RestController
public class ProcessoController {

  @Autowired
  ServicoDeProcessos service;

  @ResponseStatus(code = HttpStatus.CREATED)
  @PostMapping(path = "v1" + "/processo", headers = "api-version=2021-04-21")
  public Processo criaProcesso(@RequestBody Processo processo) {
    return service.saveProcesso(processo);
  }

  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping(path = "v1" + "/processos", headers = "api-version=2021-04-21")
  public Iterable<Processo> listaProcessos() {
    return service.getProcessoHistory();
  }

  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping(path = "v1" + "/processo/{id}", headers = "api-version=2021-04-21")
  public Processo buscaUmProcesso(@PathVariable("id") Long id) {
    return service.buscaUmProcesso(id);
  }

  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping(path = "v1" + "/processo", headers = "api-version=2021-04-21")
  public Processo viewProcessoById(@RequestParam("chaveProcesso") String chaveProcesso) {
    return service.buscaUmProcessoPorChave(chaveProcesso);
  }

  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  @PutMapping(path = "v1" + "/processo/{id}", headers = "api-version=2021-04-21")
  public void atualizaProcesso(@RequestBody Processo processoAtualizado, @PathVariable("id") Long id) {
    service.atualizaProcesso(processoAtualizado, id);
  }

  @ResponseStatus(code = HttpStatus.OK)
  @DeleteMapping(path = "v1" + "/processo/{id}", headers = "api-version=2021-04-21")
  public Processo deletaProcesso(@PathVariable("id") Long id) {
    return service.deletaProcesso(id);
  }

}
