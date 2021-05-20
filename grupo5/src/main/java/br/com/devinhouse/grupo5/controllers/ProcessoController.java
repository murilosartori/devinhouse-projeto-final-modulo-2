package br.com.devinhouse.grupo5.controllers;

import br.com.devinhouse.grupo5.dto.ProcessoInputDTO;
import br.com.devinhouse.grupo5.dto.ProcessoOutputDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.devinhouse.grupo5.service.ServicoDeProcessos;

@RestController
@RequestMapping(value = "/v1")
@ResponseBody
public class ProcessoController {

	@Autowired
	ServicoDeProcessos service;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(path = "processo")
	public ProcessoOutputDTO criaProcesso(@RequestBody ProcessoInputDTO processo) {
		return service.salvarProcesso(processo);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "processos")
	public List<ProcessoOutputDTO> listaProcessos() {
		return service.buscarTodosProcessos();
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "processo/{id}")
	public ProcessoOutputDTO buscaUmProcesso(@PathVariable("id") Long id) {
		return service.buscarUmProcesso(id);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "processo")
	public ProcessoOutputDTO buscaUmProcessoPorChave(@RequestParam("chaveProcesso") String chaveProcesso) {
		return service.buscarUmProcessoPorChave(chaveProcesso);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(path = "processo/{id}")
	public void atualizaProcesso(@RequestBody ProcessoInputDTO processoAtualizado, @PathVariable("id") Long id) {
		service.atualizarProcesso(processoAtualizado, id);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping(path = "processo/{id}")
	public ProcessoOutputDTO deletaProcesso(@PathVariable("id") Long id) {
		return service.deletarProcesso(id);
	}

}
