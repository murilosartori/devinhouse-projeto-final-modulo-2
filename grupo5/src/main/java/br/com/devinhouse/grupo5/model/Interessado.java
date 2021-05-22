package br.com.devinhouse.grupo5.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "Interessado")
public class Interessado {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	@Column(length = 250, nullable = false)
	private String nmInteressado;
	@CPF
	@Column(length = 50, nullable = false)
	private String nuIdentificacao;
	@Column(nullable = false)
	private Date dtNascimento;
	@Column(nullable = false)
	private Boolean flAtivo;
}
