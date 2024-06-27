package com.zabalburu.inscripcion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.zabalburu.inscripcion.dto.CursoDTO;
import com.zabalburu.inscripcion.exception.CursoNotFound;
import com.zabalburu.inscripcion.exception.InscripcioException;
import com.zabalburu.inscripcion.model.Inscripcion;
import com.zabalburu.inscripcion.repo.InscripcionRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Servicio {

	private final InscripcionRepo repo;
	
	//No puedo sacar datos del microservicio ms-usuarios-jwt no se porque
	//private final String URL_USUARIOS  = "http://ms-usuarios-jwt/usuarios";
	private final String URL_CURSOS  = "http://servicio-cursos/cursos";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Inscripcion> getInscipciones() {
        return repo.findAll();
    }
	
	public Inscripcion getInscripcion(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public Inscripcion inscribir(Inscripcion nueva) throws CursoNotFound, InscripcioException {
		
		 if (repo.existsByUsuarioIdAndCursoId(nueva.getUsuarioId(), nueva.getCursoId())) {
	            throw new InscripcioException ("El usuario ya está inscrito en este curso.");
	        }
		 
		try {
			 restTemplate.getForObject(URL_CURSOS + "/{id}", Object.class, nueva.getCursoId());
			
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			throw new CursoNotFound("El curso con el id: " + nueva.getCursoId() + " No Existe!");
		}
		
		
		return repo.save(nueva);
	}
	
	public boolean eliminarInscripcion(Integer id) {
		Inscripcion i = getInscripcion(id);
		if(i != null) {
			repo.delete(i);
			return true;
		}
		return false;
	}
	
	
	
	public List<CursoDTO> getCursosByUsuarioId(Integer usuarioId) {
        List<Inscripcion> inscripciones = repo.findByUsuarioId(usuarioId);
        List<CursoDTO> cursos = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            CursoDTO curso = restTemplate.getForObject(URL_CURSOS + "/" + inscripcion.getCursoId(), CursoDTO.class);
            cursos.add(curso);
        }

        return cursos;
    }
	
	
	public boolean eliminarInscripcionPorUsuarioYCurso(Integer usuarioId, Integer cursoId) {
        Inscripcion inscripcion = repo.findByUsuarioIdAndCursoId(usuarioId, cursoId);
        if (inscripcion != null) {
            repo.delete(inscripcion);
            return true;
        }
        return false;
    }
	
}
