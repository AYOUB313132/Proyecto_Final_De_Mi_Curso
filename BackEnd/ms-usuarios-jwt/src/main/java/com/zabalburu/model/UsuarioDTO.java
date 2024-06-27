package com.zabalburu.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	
	private int numPagina;
	private int totalPaginas;
	private List<Usuario> usuarios;
}