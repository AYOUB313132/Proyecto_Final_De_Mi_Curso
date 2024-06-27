package com.zabalburu.inscripcion.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	 	
		private Integer id;
	    private String nombre;
	    private String apellido;
	    private String email;
	    private String genero;
	    private Date fechaNacimiento;
	    private String direccion;
	    private Integer movil;
	    private String ciudad;
	    private Integer codigoPostal;
	    private String provincia;
	    private String pais;
	    private String imagen;
	    //private Role role;
}
