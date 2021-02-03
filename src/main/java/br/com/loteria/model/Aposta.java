package br.com.loteria.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Aposta {

	@Id
	@GeneratedValue
	private Long id;
			
	@ElementCollection
	private List<Integer> numeros;
	
	
	public Aposta() {
		
		Random gerador = new Random();
		numeros = new ArrayList<>();
				
		while(numeros.size() < 6) {
			Integer sorteado = gerador.nextInt(60) + 1;
			if(!numeros.contains(sorteado)) numeros.add(sorteado);		
		}
		Collections.sort(numeros);				
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Integer> getNumeros_sorteados() {
		return numeros;
	}
	
	public void setNumeros(List<Integer> numeros) {
		this.numeros= numeros;
	}	
	
}
