package br.com.loteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loteria.model.Aposta;

public interface ApostaRepository extends JpaRepository<Aposta, Long> {
	
}