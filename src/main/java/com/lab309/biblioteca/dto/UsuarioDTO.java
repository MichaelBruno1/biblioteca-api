package com.lab309.biblioteca.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.lab309.biblioteca.model.Usuario;

public class UsuarioDTO {

	private Long id;
	private String usuario;
	private String email;
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.usuario = usuario.getUsuario();
		this.email = usuario.getEmail();
	}

	public Long getId() {
		return id;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getEmail() {
		return email;
	}
	
	public static List<UsuarioDTO> converter(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}
	
}
