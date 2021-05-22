package br.com.devinhouse.grupo5.controllers;

import br.com.devinhouse.grupo5.dto.ProcessoInputDTO;
import br.com.devinhouse.grupo5.dto.ProcessoOutputDTO;

import org.aspectj.apache.bcel.classfile.Code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.devinhouse.grupo5.service.ProcessoService;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/v1/processo")
public class ProcessoController {

  @Autowired
  ProcessoService service;

  @ResponseStatus(code = CREATED)
  @PostMapping
  public ProcessoOutputDTO criaProcesso(@RequestBody ProcessoInputDTO processo) {
    return service.salvarProcesso(processo);
  }

  @GetMapping
  public List<ProcessoOutputDTO> listaProcessos() {
    return service.buscarTodosProcessos();
  }

  @GetMapping(path = "/id/{id}")
  public ProcessoOutputDTO buscaUmProcesso(@PathVariable("id") Long id) {
    return service.buscarUmProcesso(id);
  }

  @GetMapping(path = "/chaveprocesso")
  public ProcessoOutputDTO buscaUmProcessoPorChave(@RequestParam("chaveProcesso") String chaveProcesso) {
    return service.buscarUmProcessoPorChave(chaveProcesso);
  }

  @GetMapping(path = "/cdinteressado")
  public ProcessoOutputDTO buscaUmProcessoPorInteressado(@RequestParam("cdInteressado") Long cdInteressado) {
    return service.buscarUmProcessoPorCdInteressado(cdInteressado);
  }

  @GetMapping(path = "/cdassunto")
  public ProcessoOutputDTO buscaUmProcessoPorAssunto(@RequestParam("cdAssunto") Long cdAssunto) {
    return service.buscarUmProcessoPorCdAssunto(cdAssunto);
  }

  @ResponseStatus(code = NO_CONTENT)
  @PutMapping(path = "/id/{id}")
  public void atualizaProcesso(@RequestBody ProcessoInputDTO processoAtualizado, @PathVariable("id") Long id) {
    service.atualizarProcesso(processoAtualizado, id);
  }

  @DeleteMapping(path = "/id/{id}")
  public ProcessoOutputDTO deletaProcesso(@PathVariable("id") Long id) {
    return service.deletarProcesso(id);
  }
}
