package br.com.loteria.model;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "jogador")
public class Jogador {

	@Id
	@GeneratedValue
	private Long id;
	
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "jogador_id")
	private List<Aposta> apostas;
	
	
	public Jogador() {
		apostas = new ArrayList<>();		
		apostas.add(new Aposta());
	}
	
	public Jogador(String email) {
				
		this.email = email;
		apostas = new ArrayList<>();		
		apostas.add(new Aposta());
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Aposta> getApostas() {
		return apostas;
	}

	public void setApostas(List<Aposta> lista) {
		this.apostas = apostas;
	}
	

	public Jogador addAposta() {
		
		boolean repete = true;
		while(repete) {
			Aposta aposta = new Aposta();
			List<Integer> numbers = Arrays
					.asList(aposta.getNumero1(), aposta.getNumero2(), aposta.getNumero3(),
							aposta.getNumero4(), aposta.getNumero5(), aposta.getNumero6());
						
			for(Aposta ap: apostas) {	
				List<Integer> numerosSalvos = Arrays
						.asList(ap.getNumero1(), ap.getNumero2(), ap.getNumero3(),
								ap.getNumero4(), ap.getNumero5(), ap.getNumero6());
				for(int i = 0; i < numerosSalvos.size(); i++) {
					if(numerosSalvos.get(i) != numbers.get(i)) repete = false;
				}
			}
			if(!repete) apostas.add(aposta);
		}
		return this;
	}
	
}
