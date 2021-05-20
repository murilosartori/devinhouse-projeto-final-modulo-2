package br.com.devinhouse.grupo5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InteressadoInputDTO {

    private Long id;
    private String nmInteressado;
    private String nuIdentificacao;
    private Date dtNascimento;
    private Boolean flAtivo;


}
