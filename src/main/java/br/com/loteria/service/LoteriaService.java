package br.com.loteria.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.loteria.model.Jogador;
import br.com.loteria.repository.ApostaRepository;
import br.com.loteria.repository.JogadorRepository;


@Service
public class LoteriaService {

	@Autowired
	private JogadorRepository jogadorRepository;
	
	@Autowired
	private ApostaRepository apostaRepository;
		
	
	public Jogador save(Jogador jogador) {
		
		Jogador j = jogadorRepository.findByEmail(jogador.getEmail());
										
		if(j != null) jogador = j.addAposta();
		
		return jogadorRepository.save(jogador);
	}
	
	
	public List<Jogador> buscarJogadores() {
		
		return jogadorRepository.findAll();
	}
		
	public Jogador buscarJogadorPorId(Long id) {
		
		return jogadorRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
	}
		
	public Jogador buscarJogadorPorEmail(String email) {
		
		return jogadorRepository.findByEmail(email.toLowerCase());				
	}
	
	public void deleteJogador(Long id) {
		jogadorRepository.deleteById(id);
	}
	
	public void deleteAposta(Long id) {
		apostaRepository.deleteById(id);
	}
	
}
