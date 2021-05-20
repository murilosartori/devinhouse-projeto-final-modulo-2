package br.com.devinhouse.grupo5.dto;

import lombok.*;

//@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProcessoInputDTO {

    private Long nuProcesso;
    private String sgOrgaoProcesso;
    private String nuAnoProcesso;
    private String descricao;
    private String cdAssunto;
    private String descricaoAssunto;
    private Integer cdInteressado;
    private String nmInteressado;
    private String chaveProcesso = this.nuAnoProcesso;

    public Long getNuProcesso() {
        return nuProcesso;
    }

    public String getSgOrgaoProcesso() {
        return sgOrgaoProcesso;
    }

    public String getNuAnoProcesso() {
        return nuAnoProcesso;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCdAssunto() {
        return cdAssunto;
    }

    public String getDescricaoAssunto() {
        return descricaoAssunto;
    }

    public Integer getCdInteressado() {
        return cdInteressado;
    }

    public String getNmInteressado() {
        return nmInteressado;
    }

    public String getChaveProcesso() {
        return sgOrgaoProcesso + " " + nuProcesso + "/" + nuAnoProcesso;
    }
}
