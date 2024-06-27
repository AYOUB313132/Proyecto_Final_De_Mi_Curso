package org.zabalburu.modelo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {

	private int numPagina;
	private int totalPaginas;
	private List<Curso> cursos;
}
