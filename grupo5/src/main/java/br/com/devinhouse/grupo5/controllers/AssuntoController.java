package br.com.devinhouse.grupo5.controllers;

import br.com.devinhouse.grupo5.dto.AssuntoInputDTO;
import br.com.devinhouse.grupo5.dto.AssuntoOutputDTO;
import br.com.devinhouse.grupo5.service.AssuntoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1" + "/assunto")
public class AssuntoController {

    @Autowired
    AssuntoService assuntoService;

    @ResponseStatus(value = CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public AssuntoOutputDTO cadastrarAssunto(@RequestBody AssuntoInputDTO novoAssunto){
        return assuntoService.cadastrarAssunto(novoAssunto);
    }

    @ResponseStatus(value = OK)
    @GetMapping(value = "/id/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public AssuntoOutputDTO buscarAssuntoPorId(@PathVariable Long id){
        return assuntoService.buscarAssuntoPorId(id);
    }
}
