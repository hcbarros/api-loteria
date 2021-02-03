package br.com.loteria.controller;

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
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Jogador save(@RequestBody @Valid Jogador jogador) {
		return service.save(jogador);
	}
	
	
}


