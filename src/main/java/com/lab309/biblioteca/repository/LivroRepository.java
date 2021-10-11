package com.lab309.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab309.biblioteca.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

	List<Livro> findByTitulo(String titulo);

	List<Livro> findByAutor(String autor);

	List<Livro> findByGenero(String genero);
	
}
