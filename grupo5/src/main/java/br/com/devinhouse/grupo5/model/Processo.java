package br.com.devinhouse.grupo5.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "Processos")
public class Processo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Integer nuProcesso;
	@NotNull
	@Column(length = 4)
	private String sgOrgaoSetor;
	@NotNull
	@Column(length = 4)
	private String nuAno;
	@NotNull
	@Column(length = 45)
	private String chaveProcesso;
	@NotNull
	@Column(length = 250)
	private String descricao;
	@NotNull
	@JoinColumn(name = "Assunto_idAssunto", referencedColumnName = "id")
	@ManyToOne
	private Assunto cdAssunto;
	@NotNull
	@JoinColumn(name = "Interessado_idInteressado", referencedColumnName = "id")
	@ManyToOne
	private Interessado cdInteressado;

  @Builder(toBuilder = true)
	public Processo(Integer nuProcesso, String sgOrgaoSetor, String nuAno, String descricao, Assunto cdAssunto,
      Interessado cdInteressado) {
    this.nuProcesso = nuProcesso;
    this.sgOrgaoSetor = sgOrgaoSetor;
    this.nuAno = nuAno;
    this.descricao = descricao;
    this.cdAssunto = cdAssunto;
    this.cdInteressado = cdInteressado;
    this.chaveProcesso = this.sgOrgaoSetor + " " + this.nuProcesso + "/" + this.nuAno;
  }
}
