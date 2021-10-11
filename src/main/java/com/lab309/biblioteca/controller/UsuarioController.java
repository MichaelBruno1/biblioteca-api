package com.lab309.biblioteca.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lab309.biblioteca.model.Livro;
import com.lab309.biblioteca.model.Usuario;
import com.lab309.biblioteca.repository.LivroRepository;
import com.lab309.biblioteca.services.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	LivroRepository livroRepository;
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Optional<Usuario>> buscarLivro(@PathVariable Long id){
		return ResponseEntity.ok(usuarioService.buscarUsuario(id));
	}
	
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> buscarTodos(@RequestParam(required = false) Map<String,String> filtros){
		return ResponseEntity.ok(usuarioService.buscarTodos(filtros));
	}
	
	
	@PostMapping("/usuario")
	public ResponseEntity<Usuario> inserirLivro(@RequestBody Usuario usuario, UriComponentsBuilder uriBuilder){
		usuario = usuarioService.registraUsuario(usuario);
		
		URI uri = uriBuilder.path("/usuario").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(usuario);
	}
	
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<Usuario> atualizaLivro(@PathVariable Long id, @RequestBody Usuario usuario){
		return ResponseEntity.ok(usuarioService.atualizaUsuario(id, usuario));
	}
	
	
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<?> removerLivro(@PathVariable Long id){
		usuarioService.removerUsuario(id);
		return ResponseEntity.ok().build();
	}
	
	
	@PostMapping("/usuario/{id}/alugar")
	public ResponseEntity<Usuario> alugarLivro(@PathVariable Long id, @RequestBody Livro livro){		
		return ResponseEntity.ok(usuarioService.alugaLivro(id, livro));
	}
	
	@PostMapping("/usuario/{id}/devolver")
	public ResponseEntity<Livro> devolverLivro(@PathVariable Long id, @RequestBody Livro livro){		
		return ResponseEntity.ok(usuarioService.devolveLivro(id, livro));
	}
	
	@GetMapping("/usuario/{id}/livros")
	public ResponseEntity<List<Livro>> alugarLivro(@PathVariable Long id){		
		return ResponseEntity.ok(livroRepository.findByUsuarioId(id));
	}
}
