package com.zabalburu.inscripcion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "inscripciones")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Inscripcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	@Column(name = "curso_id")
	private Integer cursoId;

	@Column(name = "usuario_id")
	private Integer usuarioId;

	
}

