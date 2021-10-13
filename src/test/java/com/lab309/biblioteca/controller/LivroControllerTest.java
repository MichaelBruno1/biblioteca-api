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
public class LivroControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private Gson gson = new Gson();
	
	@Test
	public void testeStatusCreated() throws Exception {
		
		URI uri = new URI("/api/livro");
		Livro livroTeste = new Livro("Spring Teste", "Michael", "curso");
		String jsonObject = gson.toJson(livroTeste);
		
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
		
		URI uri = new URI("/api/livro");
		Livro livroTeste = new Livro("Spring Teste", "Michael", null);
		String jsonObject = gson.toJson(livroTeste);
		
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
		
		URI uri = new URI("/api/livro/999");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(404));
		
	}
	
	@Test
	public void testeFiltroDeBusca() throws Exception {
		
		URI uri = new URI("/api/livros?autor=michael");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
		
	}
	
}