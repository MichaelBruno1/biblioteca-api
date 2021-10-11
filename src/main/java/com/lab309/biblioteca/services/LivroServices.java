package com.lab309.biblioteca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab309.biblioteca.dto.LivroDTO;
import com.lab309.biblioteca.model.Livro;
import com.lab309.biblioteca.repository.LivroRepository;

@Service
public class LivroServices{

	@Autowired
	LivroRepository livroRepository;
	
	
	public LivroDTO buscarLivro(Long id) {
		
		Optional<Livro> livroOpcional = livroRepository.findById(id);		
	
		return new LivroDTO(livroOpcional.get());
	}

	
	public Livro inserirLivro(Livro livro) {
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
		livroRepository.deleteById(id);
	}
	
	public LivroDTO atualizaLivro(Long id, Livro livro){
		
		Optional<Livro> livroOpcional = livroRepository.findById(id);
		
		
		if (livroOpcional.isPresent()) {
			livro.setId(id);
			livroRepository.save(livro);
		}
		
		return new LivroDTO(livro);
	}
	
	public List<LivroDTO> buscaLivrosPorUsuario(Long id){
		
		List<Livro> listaDeLivros = new ArrayList<Livro>();
		
		listaDeLivros = livroRepository.findByUsuarioId(id);
		
		return LivroDTO.converter(listaDeLivros);
	}
}
