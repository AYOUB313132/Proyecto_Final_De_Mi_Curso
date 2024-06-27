package com.zabalburu.inscripcion.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {
    private Integer cursoId;
    private String nombreCurso;
    private String detallesDelCurso;
    private Date fechaInicio;
    private Date fechaFinal;
    private Integer profesorId;
    private Double precio;
    private TipoCurso tipo;
    private String imagen;
}
