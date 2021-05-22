package br.com.devinhouse.grupo5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InteressadoInputDTO {

    private String nmInteressado;
    @CPF
    private String nuIdentificacao;
    private Date dtNascimento;
    private Boolean flAtivo;


}
