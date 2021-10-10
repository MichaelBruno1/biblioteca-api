package com.lab309.biblioteca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab309.biblioteca.model.Livro;
import com.lab309.biblioteca.repository.LivroRepository;

@Service
public class LivroServices{

	@Autowired
	LivroRepository livroRepository;
	
	
	public Optional<Livro> buscarLivro(Long id) {
		return livroRepository.findById(id);
	}

	
	public Livro inserirLivro(Livro livro) {
		return livroRepository.save(livro);
	}

	public List<Livro> buscarTodos(){
		return livroRepository.findAll();
	}
	
	public void removerLivro(Long id){
		livroRepository.deleteById(id);
	}
	
	public Livro atualizaLivro(Long id, Livro livro){
		
		Optional<Livro> livroOpcional = livroRepository.findById(id);

		if (livroOpcional.isPresent()) {
			livro.setId(id);
			return livroRepository.save(livro);
		}
		
		return new Livro();
	}
}
