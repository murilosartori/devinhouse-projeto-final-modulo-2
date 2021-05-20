package br.com.devinhouse.grupo5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devinhouse.grupo5.model.Assunto;
import br.com.devinhouse.grupo5.model.Interessado;
import br.com.devinhouse.grupo5.model.Processo;

public interface RepositorioDeProcessos extends JpaRepository<Processo, Long> {
	Optional<Processo> findByChaveProcesso(String chaveProcesso);
	
	Optional<Processo> findByCdAssunto(Assunto cdAssunto);
	
	Optional<Processo> findByCdInteressado(Interessado cdInteressado);
}
