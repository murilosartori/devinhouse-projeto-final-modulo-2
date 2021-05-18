package br.com.devinhouse.grupo5.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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

	// TODO: retirar isso e repensar a forma de construção da chave de processo
	public void setChaveProcesso() {
		this.chaveProcesso = sgOrgaoProcesso + " " + nuProcesso + "/" + nuAnoProcesso;
	}
}
