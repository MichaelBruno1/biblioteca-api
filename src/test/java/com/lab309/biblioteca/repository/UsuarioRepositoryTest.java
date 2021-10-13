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
import com.lab309.biblioteca.model.Usuario;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Test
	public void testaSeEhPossivelCriarUmUsuario() {
		Usuario usuarioTeste = new Usuario("michael", "123456", "michael@email.com");
		Usuario testeResponse = usuarioRepository.save(usuarioTeste);

		Assert.assertNotNull(testeResponse);
	}
	
	@Test
	public void testaSeEhPossivelBuscarUmUsuario() {
		
		String nome = "michael";
		
		Usuario usuarioTeste = new Usuario("michael", "123456", "michael@email.com");
		usuarioRepository.save(usuarioTeste);
		
		List<Usuario> testeResponse = usuarioRepository.findByUsuario(nome);
		
		Assert.assertFalse(testeResponse.isEmpty());
	}
	
	@Test
	public void testaSeEhPossivelRemoverUmUsuario() {
		Usuario usuarioTeste = new Usuario("michael", "123456", "michael@email.com");;
		usuarioTeste = usuarioRepository.save(usuarioTeste);

		usuarioRepository.deleteById(usuarioTeste.getId());
		
		Optional<Usuario> testeResponse = usuarioRepository.findById(usuarioTeste.getId());
		
		Assert.assertFalse(testeResponse.isPresent());
	}
	
	@Test
	public void testaSeEhPossivelAtualizarUmUsuario() {
		String nome = "michael b";
		
		Usuario usuarioTeste = new Usuario("michael", "123456", "michael@email.com");
		usuarioTeste = usuarioRepository.save(usuarioTeste);
		
		usuarioTeste.setUsuario(nome);
		
		usuarioRepository.save(usuarioTeste);
		
		Optional<Usuario> testeResponse = usuarioRepository.findById(usuarioTeste.getId());
		
		Assert.assertEquals(testeResponse.get(), usuarioTeste);
	}
	
}
