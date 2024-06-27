package org.zabalburu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zabalburu.modelo.Profesor;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProfesoresRepo extends JpaRepository<Profesor, Integer>{
	Optional<Profesor> findByApellido(String apellido);
}
