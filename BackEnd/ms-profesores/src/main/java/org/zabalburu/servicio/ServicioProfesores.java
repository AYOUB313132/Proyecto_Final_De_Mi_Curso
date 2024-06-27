package org.zabalburu.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.zabalburu.modelo.Profesor;
import org.zabalburu.modelo.ProfesorDTO;
import org.zabalburu.repo.ProfesoresRepo;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ServicioProfesores {
	
	
	private final ProfesoresRepo repo;
   
	//private final PasswordEncoder passwordEncoder;
	
	private final RestTemplate template;
	
	private  String  URL_SERVICIO_IMAGENES = "http://SERVICIO-IMAGENES/imagenes";
	
	
	
	public ProfesorDTO getProfesoresDTO(Integer numPagina){
		PageRequest pg = PageRequest.of(numPagina, 9);
		Page<Profesor> page = repo.findAll(pg);
		return new ProfesorDTO(numPagina + 1, page.getTotalPages(), page.getContent());
	}
	
	public List<Profesor> getProfesores() {
        return repo.findAll(Sort.by("nombre"));
    }
	
	public Profesor getProfesor(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public Profesor añadirProfesor(Profesor nuevo) throws Exception {
		Optional<Profesor> exist = repo.findByApellido(nuevo.getApellido());
		if(exist.isEmpty()) {
			return repo.save(nuevo);
		}
		return null;
	}
	
	public boolean eliminarProfesor(Integer id) {
		Profesor p = getProfesor(id);
		if(p != null) {
			repo.delete(p);
			return true;
		}
		return false;
	}
	
	public Profesor modificarProfesor(Profesor modificar) {
			return repo.save(modificar);
	}

	
	

	
	

	 
	 

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	
}







