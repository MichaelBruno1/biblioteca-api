package com.lab309.biblioteca.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.lab309.biblioteca.model.Livro;

public class LivroDTO {
	
	private Long id;	
	private String titulo;
	private String autor;
	private String genero;
	private boolean alugado;
	
	public LivroDTO(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.autor = livro.getAutor();
		this.genero = livro.getGenero();
		this.alugado = livro.isAlugado();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getGenero() {
		return genero;
	}

	public boolean isAlugado() {
		return alugado;
	}
	
	public static List<LivroDTO> converter(List<Livro> livros) {
		return livros.stream().map(LivroDTO::new).collect(Collectors.toList());
	}
	
}
