package br.com.devinhouse.grupo5.controllers;

import br.com.devinhouse.grupo5.dto.InteressadoOutputDTO;
import br.com.devinhouse.grupo5.model.Interessado;
import br.com.devinhouse.grupo5.service.InteressadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1" + "/interessado")
public class InteressadoController {

    @Autowired
    InteressadoService interessadoService;

    @ResponseStatus(value = OK)
    @GetMapping(value = "/id/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public InteressadoOutputDTO buscarInteressadoPeloId(@PathVariable Long id){
        return interessadoService.buscarInteressadoPeloId(id);
    }

    @ResponseStatus(value = OK)
    @GetMapping(value = "/nuidentificacao", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public InteressadoOutputDTO buscarInteressadoPeloNuIdentificacao(@RequestParam String valor){
        return interessadoService.buscarInteressadoPeloNuIdentificacao(valor);
    }


}
