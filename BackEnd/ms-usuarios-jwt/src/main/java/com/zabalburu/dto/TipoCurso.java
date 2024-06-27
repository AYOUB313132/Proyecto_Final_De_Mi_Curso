package com.zabalburu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoCurso {

	private Integer tipoId;
    private String tipoNombre;
    private String imagen;
}
