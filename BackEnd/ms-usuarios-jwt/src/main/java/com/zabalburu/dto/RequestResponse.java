package com.zabalburu.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zabalburu.model.Usuario;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestResponse {

	private int statusCode;
	private String error;
	private String message;
	private String token;
	private String refreshToken;
	private String experationTime;

	//Informacion Usuario
	
	private String nombre;
    private String apellido;
    private String email;
    private String contraseña;
    private String genero;
    private Date fechaNacimiento;
    private String direccion;
    private Integer movil;
    private String ciudad;
    private Integer codigoPostal;
    private String provincia;
    private String pais;
    private String imagen;
	
	
	private Usuario usuario;
}
