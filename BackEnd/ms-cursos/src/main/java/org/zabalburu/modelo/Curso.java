package org.zabalburu.modelo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "cursos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Curso {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "curso_id")
    private Integer cursoId;

    @Column(name = "nombre_curso")
    private String nombreCurso;

    @Column(name = "detalles_del_curso")
    private String detallesDelCurso;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    
    @Column(name = "fecha_final")
    private Date fechaFinal;
    
    
    @Column(name = "profesor_id")
    private Integer profesorId;
    
    private Double precio;
    
    @ManyToOne()
    @JoinColumn(name = "tipo_id")
    private TipoCurso tipo;
    
    private String imagen;
}
