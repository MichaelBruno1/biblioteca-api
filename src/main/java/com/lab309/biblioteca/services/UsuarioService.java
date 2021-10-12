package com.lab309.biblioteca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab309.biblioteca.dto.LivroDTO;
import com.lab309.biblioteca.dto.UsuarioDTO;
import com.lab309.biblioteca.exception.NotFoundException;
import com.lab309.biblioteca.exception.ObjetoInvalidoException;
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
	
	
	public UsuarioDTO registraUsuario(Usuario usuario) {
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		
		if (!violations.isEmpty()) {
			throw new ObjetoInvalidoException("Objeto Inválido");
		}
		
		return new UsuarioDTO(usuarioRepository.save(usuario));
	}
	
	public UsuarioDTO buscarUsuario(Long id) {
		Optional<Usuario> usuarioOpcional = usuarioRepository.findById(id);
		
		if(usuarioOpcional.isEmpty()) {
			throw new NotFoundException("Usuario não encontrado");
		}
		
		return new UsuarioDTO(usuarioOpcional.get());
	}
	
	public List<UsuarioDTO> buscaTodos(){
		return UsuarioDTO.converter(usuarioRepository.findAll());
	}
	
	public void removerUsuario(Long id){
		
		try {
			usuarioRepository.deleteById(id);
			
		} catch (Exception e) {
			throw new NotFoundException("Usuario não encontrado");			
		}
		
	}
	
	public List<UsuarioDTO> buscarTodos(Map<String, String> filtros){
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
		
		return UsuarioDTO.converter(listaDeUsuarios);
	}
	
	public UsuarioDTO atualizaUsuario(Long id, Usuario usuario){
		
		Optional<Usuario> usuarioOpcional = usuarioRepository.findById(id);

		if (usuarioOpcional.isPresent()) {
			usuario.setId(id);
			usuarioRepository.save(usuario);
		}else {
			throw new NotFoundException("Usuario não encontrado");
		}
		
		return new UsuarioDTO(usuario);
	}
	
	
	public LivroDTO alugaLivro(Long id, Livro livro) {

		Optional<Usuario> usuarioOpcional = usuarioRepository.findById(id);
		Optional<Livro> livroOpcional = livroRepository.findById(livro.getId());
		
		if(usuarioOpcional.isPresent() && livroOpcional.isPresent()) {
			livro.setUsuario(usuarioOpcional.get());
			livro.setAlugado(true);
			livroRepository.save(livro);
		}else {
			throw new NotFoundException("Usuario ou livro não encontrado");
		}
		
		return new LivroDTO(livro);
	}
	
	
	public LivroDTO devolveLivro(Long id, Livro livro) {
		
		Optional<Usuario> usuarioOpcional = usuarioRepository.findById(id);
		Optional<Livro> livroOpcional = livroRepository.findById(livro.getId());
		
		if(usuarioOpcional.isPresent() && livroOpcional.isPresent()) {
			livro.setUsuario(usuarioOpcional.get());
			livro.setAlugado(true);
			livroRepository.save(livro);
		}else {
			throw new NotFoundException("Usuario ou livro não encontrado");
		}
		
		return new LivroDTO(livro);
	}
}
