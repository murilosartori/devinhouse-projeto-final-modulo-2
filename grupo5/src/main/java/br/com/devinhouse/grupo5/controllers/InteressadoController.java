package br.com.devinhouse.grupo5.controllers;

import br.com.devinhouse.grupo5.dto.InteressadoInputDTO;
import br.com.devinhouse.grupo5.dto.InteressadoOutputDTO;
import br.com.devinhouse.grupo5.service.InteressadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/interessado")
public class InteressadoController {

    @Autowired
    InteressadoService interessadoService;

    @ResponseStatus(value = CREATED)
    @PostMapping
    public InteressadoOutputDTO cadastrarAssunto(@RequestBody InteressadoInputDTO novoInteressado){
        return interessadoService.cadastrarInteressado(novoInteressado);
    }

    @GetMapping(value = "/id/{id}")
    public InteressadoOutputDTO buscarInteressadoPeloId(@PathVariable Long id){
        return interessadoService.buscarInteressadoPeloId(id);
    }

    @GetMapping(value = "/nuidentificacao")
    public InteressadoOutputDTO buscarInteressadoPeloNuIdentificacao(@RequestParam String valor){
        return interessadoService.buscarInteressadoPeloNuIdentificacao(valor);
    }
}
