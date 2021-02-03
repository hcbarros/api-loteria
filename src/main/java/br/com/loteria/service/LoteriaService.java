package br.com.loteria.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.loteria.model.Jogador;
import br.com.loteria.repository.JogadorRepository;


@Service
public class LoteriaService {

	@Autowired
	private JogadorRepository jogadorRepository;
		
	
	public Jogador save(Jogador jogador) {
		
		List<Jogador> list = jogadorRepository.findByEmail(jogador.getEmail());
										
		if(list.size() > 0) jogador = list.get(0).addAposta();
		
		return jogadorRepository.save(jogador);
	}
	
	
	
}
