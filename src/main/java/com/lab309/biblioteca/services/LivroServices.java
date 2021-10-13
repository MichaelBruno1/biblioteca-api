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
import com.lab309.biblioteca.exception.NotFoundException;
import com.lab309.biblioteca.exception.ObjetoInvalidoException;
import com.lab309.biblioteca.model.Livro;
import com.lab309.biblioteca.repository.LivroRepository;

@Service
public class LivroServices{

	@Autowired
	LivroRepository livroRepository;
	
	
	public LivroDTO buscarLivro(Long id) {
		
		Optional<Livro> livroOpcional = livroRepository.findById(id);	
		
		if(livroOpcional.isEmpty()) {
			throw new NotFoundException("Livro não encontrado");
		}
		
	
		return new LivroDTO(livroOpcional.get());
	}

	
	public Livro inserirLivro(Livro livro) {
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
		
		if (!violations.isEmpty()) {
			throw new ObjetoInvalidoException("Objeto Inválido");
		}
		
		return livroRepository.save(livro);
	}

	public List<LivroDTO> buscarTodos(Map<String, String> filtros){
		
		List<Livro> listaDeLivros = new ArrayList<Livro>();
		
		
		if(filtros.isEmpty()) {
			listaDeLivros = livroRepository.findAll();
		}
		else if(!(filtros.get("autor") == null)) {
			listaDeLivros = livroRepository.findByAutor(filtros.get("autor"));
		}		
		else if(!(filtros.get("genero") == null)) {
			listaDeLivros = livroRepository.findByGenero(filtros.get("genero"));
		}
		
		return LivroDTO.converter(listaDeLivros);
	}
	
	public void removerLivro(Long id){
		
		try {
			livroRepository.deleteById(id);
			
		} catch (Exception e) {
			throw new NotFoundException("Livro não encontrado");			
		}
		
	}
	
	
	public LivroDTO atualizaLivro(Long id, Livro livro){
		
		Optional<Livro> livroOpcional = livroRepository.findById(id);
		
		if (livroOpcional.isPresent()) {
			livro.setId(id);
			livroRepository.save(livro);
		}else {
			throw new NotFoundException("Livro não encontrado");
		}
		
		return new LivroDTO(livro);
	}
	
	
	public List<LivroDTO> buscaLivrosPorUsuario(Long id){
		
		List<Livro> listaDeLivros = new ArrayList<Livro>();
		
		listaDeLivros = livroRepository.findByUsuarioId(id);
		
		return LivroDTO.converter(listaDeLivros);
	}
}
