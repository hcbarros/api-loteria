package br.com.loteria.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.loteria.model.Jogador;
import br.com.loteria.repository.JogadorRepository;


@Configuration
@Profile("prod")
public class DataLoader {
	
	@Autowired
	private JogadorRepository repository;
		
	@Bean
	CommandLineRunner baseLoad() {
		 
		return args -> {
						
			Jogador j1 = new Jogador("jogador1@gmail.com");
			j1.addAposta();
			j1.addAposta();
			
			Jogador j2 = new Jogador("jogador2@gmail.com");
			j2.addAposta();
			j2.addAposta();
			
			List<Jogador> list = Arrays.asList(j1,j2);
			
			repository.saveAll(list);					
		};
	}

}
