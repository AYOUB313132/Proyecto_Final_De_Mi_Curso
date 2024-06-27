package org.zabalburu.controlador;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zabalburu.exception.ProfesorNotFound;
import org.zabalburu.modelo.Curso;
import org.zabalburu.modelo.CursoDTO;
import org.zabalburu.modelo.TipoCurso;
import org.zabalburu.servicio.Servicio;


@RestController
@RequestMapping("cursos")
public class Controlador {

	@Autowired
	private Servicio servicio;
	
	@GetMapping("")
	public CursoDTO getCursosDTO(@RequestParam (defaultValue = "1") Integer p){
		return servicio.getCursoDTO(p - 1);
	}
	
	/*
	@GetMapping("")
    public List<Curso> getCursos() {
        return servicio.getCursos();
    }
	*/
	
	@GetMapping("/{id}")
	public ResponseEntity<Curso> getCurso(@PathVariable Integer id){
		Curso c = servicio.getCurso(id);
		if(c != null) {
			return ResponseEntity.ok(c);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	
	
	@PostMapping("")
	public ResponseEntity<Curso> añadirCurso(@RequestBody Curso nuevo) throws URISyntaxException, ProfesorNotFound{
		System.out.println("Cursoooooooooo:  " + nuevo);
		if(nuevo != null && !nuevo.getNombreCurso().isBlank() && !nuevo.getDetallesDelCurso().isBlank()
				&& nuevo.getFechaInicio() != null && nuevo.getFechaFinal() != null
				&& nuevo.getProfesorId() != null && nuevo.getTipo() != null && nuevo.getPrecio() != null) {
					TipoCurso t = servicio.getTipoCurso(nuevo.getTipo().getTipoId());
					if(t != null) {
						servicio.añadirCurso(nuevo);
						return ResponseEntity.created(new URI("cursos/" + nuevo.getCursoId())).body(nuevo);
					}
					return ResponseEntity.notFound().build();
		}
		return ResponseEntity.unprocessableEntity().build();
	}
	
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Curso> eliminarCurso(@PathVariable Integer id){
		if(servicio.eliminarCurso(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Curso> modificarCurso(@PathVariable Integer id, @RequestBody Curso modificar) throws ProfesorNotFound{
		Curso c = servicio.getCurso(id);
		if(c != null) {
			modificar.setCursoId(id);
			
			if(!modificar.getNombreCurso().isBlank() && !modificar.getDetallesDelCurso().isBlank()
					&& modificar.getFechaInicio() != null && modificar.getFechaFinal() != null
					&& modificar.getProfesorId() != null && modificar.getTipo() != null && modificar.getPrecio() != null) {
						TipoCurso t = servicio.getTipoCurso(modificar.getTipo().getTipoId());
						if(t != null) {
							servicio.modificarCurso(modificar);
							return ResponseEntity.ok(modificar);
						}
						return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/profesor/{id}")
	public ResponseEntity<List<Curso>> getCursosByProfesor(@PathVariable Integer id) throws ProfesorNotFound{
		List<Curso> cursos = servicio.getCursosByProfesor(id);
		if(cursos != null) {
			return ResponseEntity.ok(cursos);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/tipo/{id}")
	public ResponseEntity<List<Curso>> getCursosByTipo(@PathVariable Integer id){
		List<Curso> cursos = servicio.getCursosByTipo(id);
		if(cursos != null) {
			return ResponseEntity.ok(cursos);
		}
		return ResponseEntity.notFound().build();
	}
	/* ========== Tipo Cursos ========== */
	
	
	@GetMapping("/tipo-cursos")
    public List<TipoCurso> getTipoCursos() {
        return servicio.getTipoCurso();
    }
	
	
	@GetMapping("/tipo-cursos/{id}")
	public ResponseEntity<TipoCurso> getTipoCurso(@PathVariable Integer id){
		TipoCurso tc = servicio.getTipoCurso(id);
		if(tc != null) {
			return ResponseEntity.ok(tc);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/tipo-cursos")
	public ResponseEntity<?> añadirTipoCurso(@RequestBody TipoCurso nuevo) throws URISyntaxException{
		
		if(!nuevo.getTipoNombre().isBlank()) {
			
			if(servicio.añadirTipoCurso(nuevo) != null) {
				return ResponseEntity.created(new URI("cursos/tipo-cursos/" + nuevo.getTipoId())).body(nuevo);
			}else {
				return ResponseEntity.status(HttpStatus.SC_CONFLICT).body("El Tipo del curso ya existe");
			}
				
			
		}
		return ResponseEntity.unprocessableEntity().build();
	}
	
	@DeleteMapping("/tipo-cursos/{id}")
	public ResponseEntity<TipoCurso> eliminarTipoCurso(@PathVariable Integer id){
		if(servicio.eliminarTipoCurso(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@PutMapping("/tipo-cursos/{id}")
	public ResponseEntity<TipoCurso> modificarTipoCurso(@PathVariable Integer id, @RequestBody TipoCurso modificar){
		TipoCurso tc = servicio.getTipoCurso(id);
		if(tc != null) {
			modificar.setTipoId(id);
			
			if(!modificar.getTipoNombre().isBlank()) {
				servicio.modificarTipoCurso(modificar);
				return ResponseEntity.ok(modificar);
			}
			
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/tipo-cursos/numero/{id}")
	public ResponseEntity<Integer> getNumeroCursosDeCadaTipo(@PathVariable Integer id){
		Integer n = servicio.getNumeroCursosDeCadaTipo(id);
		if(n != null) {
			return ResponseEntity.ok(n);
		}
		return ResponseEntity.notFound().build();
	}
	
}
