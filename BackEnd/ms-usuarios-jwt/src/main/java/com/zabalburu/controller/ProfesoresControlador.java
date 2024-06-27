package com.zabalburu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zabalburu.model.Role;
import com.zabalburu.model.Usuario;
import com.zabalburu.service.ServicioUsuarios;


@RestController
@RequestMapping("/profesores")
//@CrossOrigin(origins = "http://localhost:4200")
public class ProfesoresControlador {

	@Autowired
	private ServicioUsuarios servicio;
	
	
	
	@GetMapping("")
    public List<Usuario> getProfesores() {
        return servicio.getProfesores();
    }
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable Integer id){
		Usuario u = servicio.getUsuario(id);
		if(u.getRole() == Role.PROFESOR && u != null) {
			return ResponseEntity.ok(u);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	/*
	@PostMapping("")
	public ResponseEntity<Usuario> añadirUsuario(@RequestBody Usuario nuevo) throws URISyntaxException{
		if(!nuevo.getApellido().isBlank() && !nuevo.getNombre().isBlank() 
				&& !nuevo.getEmail().isBlank() && !nuevo.getContraseña().isBlank()
				&& nuevo.getFechaNacimiento() != null) {
			servicio.añadirUsuario(nuevo);
			return ResponseEntity.created(new URI("profesores/" + nuevo.getId())).body(nuevo);
		}
		return ResponseEntity.unprocessableEntity().build();
	}*/
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Integer id){
		if(servicio.eliminarUsuario(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> modificarAlumno(@PathVariable Integer id, @RequestBody Usuario modificar){
		Usuario p = servicio.getUsuario(id);
		if(p != null) {
			modificar.setId(id);
			
			if(!modificar.getNombre().isBlank() && !modificar.getApellido().isBlank()
					&& !modificar.getEmail().isBlank() && !modificar.getContraseña().isBlank()
					&& !modificar.getCiudad().isBlank() && modificar.getCodigoPostal() != null
					&& !modificar.getDireccion().isBlank() && modificar.getFechaNacimiento() != null
					&& !modificar.getGenero().isBlank() && modificar.getMovil() != null && modificar.getRole() != null
					&& !modificar.getProvincia().isBlank() && !modificar.getPais().isBlank()) {
				modificar.setContraseña(null);
				servicio.modificarUsuario(modificar);
				return ResponseEntity.ok(modificar);
			}
			
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
	
	
	
	
	
	
	
}
