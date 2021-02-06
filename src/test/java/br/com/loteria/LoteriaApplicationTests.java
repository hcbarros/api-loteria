package br.com.loteria;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.loteria.model.Aposta;
import br.com.loteria.model.Jogador;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GameStoreApplicationTests {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	
	@Test
	@Order(1)
	public void mainTest() {
	    LoteriaApplication.main(new String[] {});
	}
	
	@Test
	@Order(2)
	public void deveConterAStringJogador1gmailcom() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/loteria/1",
				String.class)).contains("jogador1@gmail.com");
	}
	

	@Test
	@Order(3)
    public void deveRetornarInternalServerErrorQuandoBuscarObjetoInexistente() {
				
		ResponseEntity<Jogador> resp = this.restTemplate
				.getForEntity("http://localhost:" + port + "/loteria/20", Jogador.class);
		assertEquals(resp.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);		  		
	}
	
	@Test
	@Order(4)
    public void deveInserirUmJogadorNoBanco() {
				
		Jogador jogador = new Jogador("jogadorAdicionado@gmail.com");
		
		List<Jogador> lista1 = getJogadores();		
		this.restTemplate
				.postForEntity("http://localhost:" + port + "/loteria", jogador, Jogador.class).getBody();	
		List<Jogador> lista2 = getJogadores();
		
		assertNotEquals(lista1.size(), lista2.size());
	}
	
	@Test
	@Order(5)
    public void deveAdicionarApostaAUmJogador() {
				
		Jogador jog1 = new Jogador("novoJogador@gmail.com");
		jog1.addAposta();
		
		Jogador jog2 = new Jogador("outroJogador@gmail.com");		
		
		assertNotEquals(jog1.getApostas().size(), jog2.getApostas().size());
	}
	
	@Test
	@Order(6)
    public void deveBuscarJogadorPorId() {
				
		Jogador jog = getJogador("1");		
		assertEquals(jog.getEmail(), "jogador1@gmail.com");
	}
	
	@Test
	@Order(7)
    public void deveBuscarJogadorPorEmail() {
				
		Jogador jog = getJogador("/email/jogador1@gmail.com");		
		assertEquals(jog.getId(), 1L);
	}
	
	@Test
	@Order(8)
    public void deveDeletarJogadorPorId() {
			
		List<Jogador> list1 = getJogadores();
					
		this.restTemplate
			.delete("http://localhost:" + port + "/loteria/4");
		
		List<Jogador> list2 = getJogadores();
		
		assertNotEquals(list1.size(), list2.size());
	}

	@Test
	@Order(9)
    public void deveDeletarAposta() {
			
		Jogador j1 = getJogador("1");
		List<Aposta> inicialSizeList = j1.getApostas();
		
		this.restTemplate
			.delete("http://localhost:" + port + "/loteria/aposta/2");
		
		Jogador j2 = getJogador("1");
		int finalSizeList = j2.getApostas().size();
		
		assertNotEquals(inicialSizeList, finalSizeList);
	}

	
	private List<Jogador> getJogadores() {

		return this.restTemplate
				.getForEntity("http://localhost:" + port + "/loteria", List.class)
				.getBody();		 
	}
	
	private Jogador getJogador(String text) {
		
		return this.restTemplate
				.getForEntity("http://localhost:" + port + "/loteria/"+text, Jogador.class)
				.getBody();		 
	}
	
}
