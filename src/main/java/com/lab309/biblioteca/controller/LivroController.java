package com.lab309.biblioteca.controller;

import java.net.URI;
import java.util.List;
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
import com.lab309.biblioteca.services.LivroServices;

@RestController
@RequestMapping("/api")
public class LivroController {

	@Autowired
	LivroServices livroServices;
	
	@GetMapping("/livro/{id}")
	public ResponseEntity<Optional<Livro>> buscarLivro(@PathVariable Long id){
		return ResponseEntity.ok(livroServices.buscarLivro(id));
	}
	
	
	@GetMapping("/livros")
	public ResponseEntity<List<Livro>> buscarTodos(){
		return ResponseEntity.ok(livroServices.buscarTodos());
	}
	
	
	@PostMapping("/livro")
	public ResponseEntity<Livro> inserirLivro(@RequestBody Livro livro, UriComponentsBuilder uriBuilder){
		livro = livroServices.inserirLivro(livro);
		
		URI uri = uriBuilder.path("/livro").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).body(livro);
	}
	
	
	@PutMapping("/livro/{id}")
	public ResponseEntity<Livro> atualizaLivro(@PathVariable Long id, @RequestBody Livro livro){
		return ResponseEntity.ok(livroServices.atualizaLivro(id, livro));
	}
	
	
	@DeleteMapping("/livro/{id}")
	public ResponseEntity<?> removerLivro(@PathVariable Long id){
		livroServices.removerLivro(id);
		return ResponseEntity.ok().build();
	}
}
