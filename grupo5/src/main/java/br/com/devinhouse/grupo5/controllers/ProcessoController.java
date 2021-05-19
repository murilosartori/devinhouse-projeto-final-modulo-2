package br.com.devinhouse.grupo5.controllers;

import br.com.devinhouse.grupo5.dto.ProcessoInputDTO;
import br.com.devinhouse.grupo5.dto.ProcessoOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.devinhouse.grupo5.model.Processo;
import br.com.devinhouse.grupo5.service.ServicoDeProcessos;

@RestController
@RequestMapping(value = "/v1")
@ResponseBody
public class ProcessoController {

	@Autowired
	ServicoDeProcessos service;

	// antigo: headers = "api-version=2021-04-21"
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(path = "processo")
	public ProcessoOutputDTO criaProcesso(@RequestBody ProcessoInputDTO processo) {
		return service.saveProcesso(processo);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "processos")
	public Iterable<Processo> listaProcessos() {
		return service.getProcessoHistory();
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "processo/{id}")
	public ProcessoOutputDTO buscaUmProcesso(@PathVariable("id") Long id) {
		return service.buscaUmProcesso(id);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "processo")
	public ProcessoOutputDTO viewProcessoById(@RequestParam("chaveProcesso") String chaveProcesso) {
		return service.buscaUmProcessoPorChave(chaveProcesso);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(path = "processo/{id}")
	public void atualizaProcesso(@RequestBody Processo processoAtualizado, @PathVariable("id") Long id) {
		service.atualizaProcesso(processoAtualizado, id);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping(path = "processo/{id}")
	public ProcessoOutputDTO deletaProcesso(@PathVariable("id") Long id) {
		return service.deletaProcesso(id);
	}

}
