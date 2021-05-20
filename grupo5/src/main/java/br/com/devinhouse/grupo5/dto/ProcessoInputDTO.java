package br.com.devinhouse.grupo5.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProcessoInputDTO {

  private Long nuProcesso;
  private String sgOrgaoSetor;
  private String nuAno;
  private String descricao;
  private Long cdAssunto;
  private Long cdInteressado;
  
    public String getChaveProcesso() {
      return sgOrgaoSetor + " " + nuProcesso + "/" + nuAno;
    }

}
