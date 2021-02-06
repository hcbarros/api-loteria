package br.com.loteria.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Jogador {

	@Id
	@GeneratedValue
	private Long id;
	
    @Pattern(regexp="^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
    message = "Email inválido!")
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
		return email.toLowerCase();
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}

	public List<Aposta> getApostas() {
		return apostas;
	}

	public void setApostas(List<Aposta> lista) {
		this.apostas = apostas;
	}

	
	public Jogador addAposta() {
		
		boolean repete = true;
		Aposta aposta = new Aposta();
		List<Integer> numbers = aposta.getNumeros_sorteados();
						
		for(Aposta ap: apostas) {				
			if(!ap.getNumeros_sorteados().equals(numbers)) repete = false;				
		}
		if(!repete) apostas.add(aposta);
		
		return repete ? addAposta() : this;
	}
	
}
