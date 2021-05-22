package br.com.devinhouse.grupo5.service;

import br.com.devinhouse.grupo5.domain.exceptions.InteressadoNaoEncontradoException;
import br.com.devinhouse.grupo5.dto.InteressadoInputDTO;
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
    
    public InteressadoOutputDTO cadastrarInteressado(InteressadoInputDTO novoInteressado) {
		// 1 - Não poderá ser cadastrado um novo interessado com um id já existente;
    	var interessado = buscarInteressadoPeloId(novoInteressado.getId());
    	if (interessado != null) {
    		throw new InteressadoNaoEncontradoException("Há um interessado cadastrado com o mesmo ID.");
    	}
		// 2 - Não poderá ser cadastrado um novo interessado com um mesmo documento de indentificação;
    	Boolean existNuIdentificacao = repositorioDeInteressado.existsByNuIdentificacao(novoInteressado.getNuIdentificacao());
    	if (Boolean.TRUE.equals(existNuIdentificacao)) {
    		throw new InteressadoNaoEncontradoException("A identificação informada já está cadastrada.");
    	}
        return toDTO(repositorioDeInteressado.save(toInteressado(novoInteressado)));
    }

    public InteressadoOutputDTO buscarInteressadoPeloId(Long id){
        return toDTO(repositorioDeInteressado.findById(id).orElseThrow(InteressadoNaoEncontradoException::new));
    }

    public InteressadoOutputDTO buscarInteressadoPeloNuIdentificacao(String valor) {
        return toDTO(repositorioDeInteressado.findByNuIdentificacao(valor).orElseThrow(InteressadoNaoEncontradoException::new));
    }

    private InteressadoOutputDTO toDTO(Interessado interessado){
        return modelMapper.map(interessado, InteressadoOutputDTO.class);
    }

    private Interessado toInteressado(InteressadoInputDTO interessadoOutputDTO){
        return modelMapper.map(interessadoOutputDTO, Interessado.class);
    }
}
