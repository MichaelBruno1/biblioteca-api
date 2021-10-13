package com.lab309.biblioteca.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;
import com.lab309.biblioteca.model.Livro;
import com.lab309.biblioteca.model.Usuario;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private Gson gson = new Gson();
	
	@Test
	public void testeStatusCreated() throws Exception {
		
		URI uri = new URI("/api/usuario");
		Usuario usuarioTeste = new Usuario("michael", "123456", "michael@email.com");
		String jsonObject = gson.toJson(usuarioTeste);
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(jsonObject)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(201));
		
	}
	
	@Test
	public void testeStatusBadRequest() throws Exception {
		
		URI uri = new URI("/api/usuario");
		Usuario usuarioTeste = new Usuario("michael", "123456", "michae");
		String jsonObject = gson.toJson(usuarioTeste);
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(jsonObject)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
		
	}
	
	@Test
	public void testeStatusNotFound() throws Exception {
		
		URI uri = new URI("/api/usuario/999");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(404));
		
	}
	
	@Test
	public void testeFiltroDeBusca() throws Exception {
		
		URI uri = new URI("/api/usuarios?usuario=michael");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
		
	}
	
	@Test
	public void testeAlugarLivro() throws Exception {
		
		URI uri = new URI("/api/usuario/1/alugar");
		Livro livroTeste = new Livro("Spring Teste", "Michael", "curso");
		livroTeste.setId((long) 1);
		String jsonObject = gson.toJson(livroTeste);
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(jsonObject)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
		
	}
	
	@Test
	public void testeDevolverLivro() throws Exception {
		
		URI uri = new URI("/api/usuario/1/devolver");
		Livro livroTeste = new Livro("Spring Teste", "Michael", "curso");
		livroTeste.setId((long) 1);
		String jsonObject = gson.toJson(livroTeste);
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(jsonObject)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
		
	}
	
}
