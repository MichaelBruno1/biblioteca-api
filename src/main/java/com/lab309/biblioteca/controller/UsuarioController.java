package com.lab309.biblioteca.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

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

import com.lab309.biblioteca.dto.LivroDTO;
import com.lab309.biblioteca.dto.UsuarioDTO;
import com.lab309.biblioteca.model.Livro;
import com.lab309.biblioteca.model.Usuario;
import com.lab309.biblioteca.services.LivroServices;
import com.lab309.biblioteca.services.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	LivroServices livroServices;
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<UsuarioDTO> buscarLivro(@PathVariable Long id){
		return ResponseEntity.ok(usuarioService.buscarUsuario(id));
	}
	
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioDTO>> buscarTodos(@RequestParam(required = false) Map<String,String> filtros){
		return ResponseEntity.ok(usuarioService.buscarTodos(filtros));
	}
	
	
	@PostMapping("/usuario")
	public ResponseEntity<?> inserirLivro(@RequestBody Usuario usuario, UriComponentsBuilder uriBuilder){
		
		UsuarioDTO usuarioDTO = usuarioService.registraUsuario(usuario);
		
		URI uri = uriBuilder.path("/usuario").buildAndExpand(usuarioDTO.getId()).toUri();
		
		return ResponseEntity.created(uri).body(usuarioDTO);
	}
	
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<UsuarioDTO> atualizaLivro(@PathVariable Long id, @RequestBody Usuario usuario){
		return ResponseEntity.ok(usuarioService.atualizaUsuario(id, usuario));
	}
	
	
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<?> removerLivro(@PathVariable Long id){
		usuarioService.removerUsuario(id);
		return ResponseEntity.ok().build();
	}
	
	
	@PostMapping("/usuario/{id}/alugar")
	public ResponseEntity<LivroDTO> alugarLivro(@PathVariable Long id, @RequestBody Livro livro){		
		return ResponseEntity.ok(usuarioService.alugaLivro(id, livro));
	}
	
	@PostMapping("/usuario/{id}/devolver")
	public ResponseEntity<LivroDTO> devolverLivro(@PathVariable Long id, @RequestBody Livro livro){		
		return ResponseEntity.ok(usuarioService.devolveLivro(id, livro));
	}
	
	@GetMapping("/usuario/{id}/livros")
	public ResponseEntity<List<LivroDTO>> alugarLivro(@PathVariable Long id){		
		return ResponseEntity.ok(livroServices.buscaLivrosPorUsuario(id));
	}
}
