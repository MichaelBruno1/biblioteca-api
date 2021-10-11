package com.lab309.biblioteca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab309.biblioteca.model.Livro;
import com.lab309.biblioteca.model.Usuario;
import com.lab309.biblioteca.repository.LivroRepository;
import com.lab309.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	LivroRepository livroRepository;
	
	
	public Usuario registraUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Optional<Usuario> buscarUsuario(Long id) {
		return usuarioRepository.findById(id);
	}
	
	public List<Usuario> buscaTodos(){
		return usuarioRepository.findAll();
	}
	
	public void removerUsuario(Long id){
		usuarioRepository.deleteById(id);
	}
	
	public List<Usuario> buscarTodos(Map<String, String> filtros){
		List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
		
		if(filtros.isEmpty()) {
			listaDeUsuarios = usuarioRepository.findAll();
		}
		else if(!(filtros.get("usuario") == null)) {
			listaDeUsuarios = usuarioRepository.findByUsuario(filtros.get("usuario"));
		}		
		else if(!(filtros.get("email") == null)) {
			listaDeUsuarios = usuarioRepository.findByEmail(filtros.get("email"));
		}		
		
		return listaDeUsuarios;
	}
	
	public Usuario atualizaUsuario(Long id, Usuario usuario){
		
		Optional<Usuario> usuarioOpcional = usuarioRepository.findById(id);

		if (usuarioOpcional.isPresent()) {
			usuario.setId(id);
			return usuarioRepository.save(usuario);
		}
		
		return usuario;
	}
	
	
	public Usuario alugaLivro(Long id, Livro livro) {
		
		Optional<Usuario> usuarioOpcional = usuarioRepository.findById(id);
		
		if(usuarioOpcional.isPresent()) {
			livro.setUsuario(usuarioOpcional.get());
			livroRepository.save(livro);
		}
		
		return usuarioOpcional.get();
	}
	
	
	public Livro devolveLivro(Long id, Livro livro) {
		
		Optional<Usuario> usuarioOpcional = usuarioRepository.findById(id);
		
		if(usuarioOpcional.isPresent()) {
			livro.setUsuario(null);
			livroRepository.save(livro);
		}
		
		return livro;
	}
}
