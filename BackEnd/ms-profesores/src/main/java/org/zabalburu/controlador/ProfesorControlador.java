package org.zabalburu.controlador;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.zabalburu.modelo.Profesor;
import org.zabalburu.servicio.ServicioProfesores;

@RestController
@RequestMapping("profesores")
public class ProfesorControlador {

	@Autowired
	private ServicioProfesores servicio;
	
	@Autowired
	private RestTemplate template;
	
	private  String  URL_SERVICIO_IMAGENES = "http://SERVICIO-IMAGENES/imagenes";
	
	/*
	@GetMapping("")
	public ProfesorDTO getProfesorDTO(@RequestParam (defaultValue = "1") Integer p){
		return servicio.getProfesoresDTO(p-1);
	}
	
	*/
	@GetMapping("")
    public List<Profesor> getProfesores() {
       return servicio.getProfesores();
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Profesor> getProfesor(@PathVariable Integer id){
		Profesor p = servicio.getProfesor(id);
		if(p != null) {
			return ResponseEntity.ok(p);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("")
	public ResponseEntity<?> añadirUsuario(@RequestBody Profesor nuevo) throws Exception{
		if(!nuevo.getApellido().isBlank() && !nuevo.getNombre().isBlank() 
				&& !nuevo.getEspecialidad().isBlank() ) { 
			
			
			if(servicio.añadirProfesor(nuevo) != null) {
				
				return ResponseEntity.created(new URI("profesores/" + nuevo.getId())).body(nuevo);
			}else{
				return ResponseEntity.status(HttpStatus.SC_CONFLICT).body("El profesor con este Apellido:" + nuevo.getApellido() + " es existe!!");
			}
		}
		return ResponseEntity.unprocessableEntity().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Profesor> eliminarProfesor(@PathVariable Integer id){
		if(servicio.eliminarProfesor(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Profesor> modificarAlumno(@PathVariable Integer id, @RequestBody Profesor modificar){
		Profesor p = servicio.getProfesor(id);
		if(p != null) {
			modificar.setId(id);
			
			if(!modificar.getNombre().isBlank() && !modificar.getApellido().isBlank()
					&& !modificar.getEspecialidad().isBlank()
					) {
				
				servicio.modificarProfesor(modificar);
				return ResponseEntity.ok(modificar);
			}
			
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	

	
	
	

	
	
}
