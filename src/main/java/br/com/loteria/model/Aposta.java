package br.com.loteria.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aposta")
public class Aposta {

	@Id
	@GeneratedValue
	private Long id;
	
	private Integer numero1;
	
	private Integer numero2;

	private Integer numero3;
	
	private Integer numero4;
	
	private Integer numero5;
	
	private Integer numero6;
	
	
	public Aposta() {
		
		Random gerador = new Random();
		List<Integer> list = new ArrayList<>();
				
		while(list.size() < 6) {
			Integer sorteado = gerador.nextInt(60) + 1;
			if(!list.contains(sorteado)) list.add(sorteado);		
		}
		Collections.sort(list);		
		this.numero1 = list.get(0);
		this.numero2 = list.get(1);
		this.numero3 = list.get(2);
		this.numero4 = list.get(3);
		this.numero5 = list.get(4);
		this.numero6 = list.get(5);
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Integer getNumero1() {
		return numero1;
	}


	public void setNumero1(Integer numero1) {
		this.numero1 = numero1;
	}


	public Integer getNumero2() {
		return numero2;
	}


	public void setNumero2(Integer numero2) {
		this.numero2 = numero2;
	}


	public Integer getNumero3() {
		return numero3;
	}


	public void setNumero3(Integer numero3) {
		this.numero3 = numero3;
	}


	public Integer getNumero4() {
		return numero4;
	}


	public void setNumero4(Integer numero4) {
		this.numero4 = numero4;
	}


	public Integer getNumero5() {
		return numero5;
	}


	public void setNumero5(Integer numero5) {
		this.numero5 = numero5;
	}


	public Integer getNumero6() {
		return numero6;
	}


	public void setNumero6(Integer numero6) {
		this.numero6 = numero6;
	}

}
