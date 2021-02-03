package br.com.loteria.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.loteria.service.LoteriaService;
import br.com.loteria.model.Jogador;


@RestController
@Validated
@RequestMapping("/loteria")
public class LoteriaController {

	@Autowired
	private LoteriaService service;
	
	
	@GetMapping(value = "{id}")
	public Jogador buscarJogadorPorId(@PathVariable Long id) {
		return service.buscarJogadorPorId(id);		
	}
	
	@GetMapping(value = "email/{email}")
	public Jogador buscarJogadorPorEmail(@PathVariable String email) {
		return service.buscarJogadorPorEmail(email);		
	}
	
	@GetMapping
	public List<Jogador> buscarJogadores() {
		return service.buscarJogadores();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Jogador save(@RequestBody @Valid Jogador jogador) {
		return service.save(jogador);
	}
	
	@DeleteMapping(value = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteJogadorPorId(@PathVariable Long id) {
		service.deleteJogadorPorId(id);
	}
	
	@DeleteMapping(value = "email/{email}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteJogadorPorEmail(@PathVariable String email) {
		service.deleteJogadorPorEmail(email);
	}
	
	@DeleteMapping(value = "aposta/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAposta(@PathVariable Long id) {
		service.deleteAposta(id);
	}
	
}


