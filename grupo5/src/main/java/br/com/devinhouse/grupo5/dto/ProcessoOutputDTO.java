package br.com.devinhouse.grupo5.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProcessoOutputDTO {

  private Long id;
  private Long nuProcesso;
  private String sgOrgaoSetor;
  private String nuAno;
  private String chaveProcesso;
  private String descricao;
  private Long cdAssunto;
  private Long cdInteressado;

}
