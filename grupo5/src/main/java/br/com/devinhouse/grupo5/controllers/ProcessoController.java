package br.com.devinhouse.grupo5.controllers;

import br.com.devinhouse.grupo5.dto.ProcessoInputDTO;
import br.com.devinhouse.grupo5.dto.ProcessoOutputDTO;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.devinhouse.grupo5.model.Processo;
import br.com.devinhouse.grupo5.service.ServicoDeProcessos;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/v1")
@ResponseBody
public class ProcessoController {

	@Autowired
	ServicoDeProcessos service;

	// antigo: headers = "api-version=2021-04-21"
	@ResponseStatus(code = CREATED)
	@PostMapping(path = "processo")
	public ProcessoOutputDTO criaProcesso(@RequestBody ProcessoInputDTO processo) {
		return service.salvarProcesso(processo);
	}

	@ResponseStatus(code = OK)
	@GetMapping(path = "processos")
	public Iterable<Processo> listaProcessos() {
		return service.getHistoricoProcesso();
	}

	@ResponseStatus(code = OK)
	@GetMapping(path = "processo/{id}")
	public ProcessoOutputDTO buscaUmProcesso(@PathVariable("id") Long id) {
		return service.buscarUmProcesso(id);
	}

	@ResponseStatus(code = OK)
	@GetMapping(path = "processo")
	public ProcessoOutputDTO buscaUmProcessoPorChave(@RequestParam("chaveProcesso") String chaveProcesso) {
		return service.buscarUmProcessoPorChave(chaveProcesso);
	}

	@ResponseStatus(code = NO_CONTENT)
	@PutMapping(path = "processo/{id}")
	public void atualizaProcesso(@RequestBody Processo processoAtualizado, @PathVariable("id") Long id) {
		service.atualizarProcesso(processoAtualizado, id);
	}

	@ResponseStatus(code = OK)
	@DeleteMapping(path = "processo/{id}")
	public ProcessoOutputDTO deletaProcesso(@PathVariable("id") Long id) {
		return service.deletarProcesso(id);
	}
}
