package br.com.devinhouse.grupo5.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssuntoOutputDTO {

    private Long id;
    private String descricao;
    private Date dtCadastro;
    private Boolean flAtivo;

}
