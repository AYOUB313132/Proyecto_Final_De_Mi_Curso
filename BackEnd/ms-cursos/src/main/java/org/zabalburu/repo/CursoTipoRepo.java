package org.zabalburu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zabalburu.modelo.TipoCurso;
import java.util.List;
import java.util.Optional;


public interface CursoTipoRepo extends JpaRepository<TipoCurso, Integer> {
	Optional<TipoCurso> findByTipoNombre(String tipoNombre);
}
