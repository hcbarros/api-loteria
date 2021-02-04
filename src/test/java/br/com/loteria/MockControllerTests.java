package br.com.loteria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.loteria.controller.LoteriaController;
import br.com.loteria.model.Jogador;
import br.com.loteria.service.LoteriaService;


@WebMvcTest(controllers = LoteriaController.class)
class MockControllerTests {
		
	@MockBean
	private LoteriaService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mvc;
	
	
	@Test
	public void deveCriarUmJogador() throws Exception {
	
		Jogador jogador = new Jogador("jogadorCriado@gmail.com"); 
		
		String product = objectMapper.writeValueAsString(jogador);
		
		mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/loteria/")
				.contentType("application/json")
				.content(product))
			.andExpect(MockMvcResultMatchers.status().isCreated());	
	}

	
	@Test
	public void deveLancarExcecaoQuandoTentarInserirJogadorComEmailInvalido() throws Exception {
	
		Jogador jogador = new Jogador("emailinvalido@.gmail"); 
		
		String product = objectMapper.writeValueAsString(jogador);
		
		mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/loteria/")
				.contentType("application/json")
				.content(product))
				.andExpect(result -> 
					assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());	
	}
	
	@Test
	public void deveBuscarTodosOsJogadores() throws Exception {		
		getJogadores("");
	}
	
	@Test
	public void deveBuscarJogadorPorId() throws Exception {		
		getJogadores("1");
	}
	
	@Test
	public void deveBuscarJogadorPorEmail() throws Exception {		
		getJogadores("email/jogadorAdicionado@gmail.com");
	}
	
	@Test
	public void deveDeletarJogadorPorId() throws Exception {
		delete("4");		
	}
	
	@Test
	public void deveDeletarApostaPorId() throws Exception {					
		delete("2");
	}

	
	private void getJogadores(String text) throws Exception  {
		
		mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/loteria/"+text)
				.contentType("application/json")
				.accept("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());	
	}
	
	public void delete(String text) throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/loteria/"+text)
				.contentType("application/json")
				.accept("application/json"))
				.andExpect(MockMvcResultMatchers.status().isNoContent());	
	}
	
}
