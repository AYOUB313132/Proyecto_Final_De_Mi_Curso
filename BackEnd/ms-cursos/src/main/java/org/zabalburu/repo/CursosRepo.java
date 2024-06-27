package org.zabalburu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zabalburu.modelo.Curso;
import org.zabalburu.modelo.TipoCurso;

public interface CursosRepo extends JpaRepository<Curso, Integer> {
	List<Curso> findByProfesorId(Integer profesorId);
	List<Curso> findByTipo(TipoCurso tipo);
}
