package com.lab309.biblioteca.repository;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lab309.biblioteca.model.Livro;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LivroRepositoryTest {

	@Autowired
	LivroRepository livroRepository;
	
	@Test
	public void testaSeEhPossivelCriarUmLivro() {
		Livro livroTeste = new Livro("Spring Teste", "Michael", "curso");
		Livro testeResponse = livroRepository.save(livroTeste);

		Assert.assertNotNull(testeResponse);
	}
	
	@Test
	public void testaSeEhPossivelBuscarUmLivroPorId() {
		
		long id = 1;

		Livro livroTeste = new Livro("Spring Teste", "Michael", "curso");
		livroTeste.setId(id);
		
		Optional<Livro> testeResponse = livroRepository.findById(id);
		
		Assert.assertNotNull(testeResponse);
		Assert.assertEquals(livroTeste.getId(), testeResponse.get().getId());
		
	}
	
	@Test
	public void testaSeEhPossivelBuscarTodosOsLivros() {
		
		List<Livro> testeResponse = livroRepository.findAll();
		
		Assert.assertNotNull(testeResponse);
	}
	
	@Test
	public void testaSeEhPossivelAtualizarUmLivro() {
		
		Livro livroTeste = new Livro("Spring Teste", "Michael", "curso");
		
		Livro testeResponse = livroRepository.save(livroTeste);
		
		livroTeste.setId(testeResponse.getId());
		livroTeste.setAutor("Michael B.");
		
		testeResponse = livroRepository.save(livroTeste);
		
		
		
		Assert.assertEquals(testeResponse, livroTeste);
	}
	
	
	@Test
	public void testaSeEhPossivelRemoverUmLivro() {
		long id = 1;
		
		livroRepository.deleteById(id);
		
		Optional<Livro> testeResponse = livroRepository.findById(id);
		
		Assert.assertFalse(testeResponse.isPresent());
	}
	
} 
