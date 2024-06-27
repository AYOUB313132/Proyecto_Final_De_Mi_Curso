package com.zabalburu.inscripcion.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zabalburu.inscripcion.dto.CursoDTO;
import com.zabalburu.inscripcion.exception.CursoNotFound;
import com.zabalburu.inscripcion.exception.InscripcioException;
import com.zabalburu.inscripcion.model.Inscripcion;
import com.zabalburu.inscripcion.service.Servicio;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inscripciones")
@RequiredArgsConstructor
public class Controlador {

	private final Servicio servicio;
	
	@GetMapping("")
    public List<Inscripcion> getInscripciones() {
        return servicio.getInscipciones();
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Inscripcion> getInscripcion(@PathVariable Integer id){
		Inscripcion i = servicio.getInscripcion(id);
		if(i != null) {
			return ResponseEntity.ok(i);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping("")
	public ResponseEntity<?> añadirInscripcion(@RequestBody Inscripcion nuevo) throws URISyntaxException, CursoNotFound, InscripcioException{
		
		try {
					if(nuevo != null && nuevo.getCursoId() != null && nuevo.getUsuarioId() != null) {
						servicio.inscribir(nuevo);
				return ResponseEntity.created(new URI("inscripciones/" + nuevo.getCursoId())).body(nuevo);
			}
		}catch(InscripcioException ex){
			 return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(ex.getMessage());
        
		}
		return ResponseEntity.unprocessableEntity().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Inscripcion> eliminarInscripcion(@PathVariable Integer id){
		if(servicio.eliminarInscripcion(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/usuario/{usuarioId}/cursos")
    public ResponseEntity<List<CursoDTO>> getCursosByUsuarioId(@PathVariable Integer usuarioId) {
        List<CursoDTO> cursos = servicio.getCursosByUsuarioId(usuarioId);
        if (cursos != null) {
            return ResponseEntity.ok(cursos);
        }
        return ResponseEntity.notFound().build();
    }
	
	@DeleteMapping("/usuario/{usuarioId}/curso/{cursoId}")
    public ResponseEntity<Void> eliminarInscripcionPorUsuarioYCurso(@PathVariable Integer usuarioId, @PathVariable Integer cursoId) {
        if (servicio.eliminarInscripcionPorUsuarioYCurso(usuarioId, cursoId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
	
	
}

