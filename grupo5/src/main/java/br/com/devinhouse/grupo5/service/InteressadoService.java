package br.com.devinhouse.grupo5.service;

import br.com.devinhouse.grupo5.domain.exceptions.InteressadoNaoEncontradoException;
import br.com.devinhouse.grupo5.dto.InteressadoOutputDTO;
import br.com.devinhouse.grupo5.model.Interessado;
import br.com.devinhouse.grupo5.repository.RepositorioDeInteressado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteressadoService {

    @Autowired
    RepositorioDeInteressado repositorioDeInteressado;

    @Autowired
    ModelMapper modelMapper;

    public InteressadoOutputDTO buscarInteressadoPeloId(Long id){
        return toDTO(repositorioDeInteressado.findById(id).orElseThrow(InteressadoNaoEncontradoException::new));
    }

    public InteressadoOutputDTO buscarInteressadoPeloNuIdentificacao(String valor) {
        return toDTO(repositorioDeInteressado.findByNuIdentificacao(valor).orElseThrow(InteressadoNaoEncontradoException::new));
    }

    private InteressadoOutputDTO toDTO(Interessado interessado){
        return modelMapper.map(interessado, InteressadoOutputDTO.class);
    }

    private Interessado toInteressado(InteressadoOutputDTO interessadoOutputDTO){
        return modelMapper.map(interessadoOutputDTO, Interessado.class);
    }
}
