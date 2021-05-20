package br.com.devinhouse.grupo5.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProcessoOutputDTO {

    private Integer nuProcesso;
    private String sgOrgaoProcesso;
    private String nuAnoProcesso;
    private String descricao;
    private String cdAssunto;
    private String descricaoAssunto;
    private Integer cdInteressado;
    private String nmInteressado;
    private String chaveProcesso;

}
