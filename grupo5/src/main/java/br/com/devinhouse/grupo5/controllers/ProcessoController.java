package br.com.devinhouse.grupo5.controllers;

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
	public Processo criaProcesso(@RequestBody Processo processo) {
		return service.salvarProcesso(processo);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "processos")
	public Iterable<Processo> listaProcessos() {
		return service.getHistoricoProcesso();
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "processo/{id}")
	public Processo buscaUmProcesso(@PathVariable("id") Long id) {
		return service.buscarUmProcesso(id);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "processo")
	public Processo viewProcessoById(@RequestParam("chaveProcesso") String chaveProcesso) {
		return service.buscarUmProcessoPorChave(chaveProcesso);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(path = "processo/{id}")
	public void atualizaProcesso(@RequestBody Processo processoAtualizado, @PathVariable("id") Long id) {
		service.atualizarProcesso(processoAtualizado, id);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping(path = "processo/{id}")
	public Processo deletaProcesso(@PathVariable("id") Long id) {
		return service.deletarProcesso(id);
	}

}
