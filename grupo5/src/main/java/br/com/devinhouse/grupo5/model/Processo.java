package br.com.devinhouse.grupo5.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

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
@Table(name = "Processo")
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

	// TODO: retirar isso e repensar a forma de construção da chave de processo
	public void setChaveProcesso() {
		this.chaveProcesso = sgOrgaoSetor + " " + nuProcesso + "/" + nuAno;
	}
}
