package com.zabalburu.inscripcion.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zabalburu.inscripcion.model.Inscripcion;
import java.util.List;


@Repository
public interface InscripcionRepo extends JpaRepository<Inscripcion, Integer> {

	List<Inscripcion> findByUsuarioId(Integer usuarioId);
	
	Inscripcion  findByUsuarioIdAndCursoId(Integer usuarioId, Integer cursoId);
	
	boolean existsByUsuarioIdAndCursoId(Integer usuarioId, Integer cursoId);
}
