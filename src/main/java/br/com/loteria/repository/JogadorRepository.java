package br.com.loteria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loteria.model.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
	
	Jogador findByEmail(String email);
	
	void deleteByEmail(String email);
}