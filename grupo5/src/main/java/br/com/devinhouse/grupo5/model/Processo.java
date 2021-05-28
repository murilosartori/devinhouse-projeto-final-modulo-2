package br.com.devinhouse.grupo5.model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "Processos")
public class Processo {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Long nuProcesso;
	@Column(length = 4, nullable = false)
	private String sgOrgaoSetor;
	@Column(length = 4, nullable = false)
	private String nuAno;
	@Column(length = 45, nullable = false)
	private String chaveProcesso;
	@Column(length = 250, nullable = false)
	private String descricao;
	//FIXME: Tirei temporariamente o impedimento à definição como nulo para testar a ferramenta
	@JoinColumn(name = "Assunto_idAssunto_id", referencedColumnName = "id")
	@ManyToOne
	private Assunto cdAssunto;
	@JoinColumn(name = "Interessado_idInteressado_id", referencedColumnName = "id")
	@ManyToOne
	private Interessado cdInteressado;

	@Builder(toBuilder = true)
	public Processo(Long nuProcesso, String sgOrgaoSetor, String nuAno, String descricao, Assunto cdAssunto,
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
