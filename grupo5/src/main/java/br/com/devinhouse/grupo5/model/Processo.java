package br.com.devinhouse.grupo5.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.*;

import lombok.*;

@Entity
@Builder(toBuilder = true)
//@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "Processos")
public class Processo {

	@Id
	private Long nuProcesso;
	private String sgOrgaoProcesso;
	private String nuAnoProcesso;
	private String descricao;
	private Integer cdAssunto;
	private String descricaoAssunto;
	private Integer cdInteressado;
	private String nmInteressado;
	private String chaveProcesso;

	public Processo(Long nuProcesso, String sgOrgaoProcesso, String nuAnoProcesso, String descricao,
					Integer cdAssunto, String descricaoAssunto, Integer cdInteressado, String nmInteressado, String chaveProcesso) {
		this.nuProcesso = nuProcesso;
		this.sgOrgaoProcesso = sgOrgaoProcesso;
		this.nuAnoProcesso = nuAnoProcesso;
		this.descricao = descricao;
		this.cdAssunto = cdAssunto;
		this.descricaoAssunto = descricaoAssunto;
		this.cdInteressado = cdInteressado;
		this.nmInteressado = nmInteressado;
		this.chaveProcesso = this.sgOrgaoProcesso + " " + this.nuProcesso + "/" + this.nuAnoProcesso;
	}

	public void setChaveProcesso(String chaveProcesso) {
		this.chaveProcesso = chaveProcesso;
	}

	// TODO: retirar isso e repensar a forma de construção da chave de processo
//	public void setChaveProcesso() {
//		this.chaveProcesso = sgOrgaoProcesso + " " + nuProcesso + "/" + nuAnoProcesso;
//	}
}
