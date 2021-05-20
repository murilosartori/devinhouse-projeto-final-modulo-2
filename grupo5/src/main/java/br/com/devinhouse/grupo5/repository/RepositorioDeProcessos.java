package br.com.devinhouse.grupo5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devinhouse.grupo5.model.Processo;

public interface RepositorioDeProcessos extends JpaRepository<Processo, Long> {
  
  Optional<Processo> findByChaveProcesso(String chaveProcesso);
}
