package com.zabalburu.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zabalburu.dto.CursoDTO;
import com.zabalburu.model.Role;
import com.zabalburu.model.Usuario;
import com.zabalburu.model.UsuarioDTO;
import com.zabalburu.service.ServicioUsuarios;


@RestController
@RequestMapping("/usuarios")
//@CrossOrigin(origins = "http://localhost:4200")
public class UsuariosControlador {

	@Autowired
	private ServicioUsuarios servicio;
	
	@Autowired
    private RestTemplate restTemplate;

    private final String URL_INSCRIPCIONES = "http://servicio-inscripciones/inscripciones";

    @GetMapping("/{usuarioId}/cursos")
    public ResponseEntity<List<CursoDTO>> getCursosUsuario(@PathVariable Integer usuarioId) {
    	Usuario u = servicio.getUsuario(usuarioId);
    	if(u != null) {
    		try {
       		 List<CursoDTO> cursos = restTemplate.getForObject(URL_INSCRIPCIONES + "/usuario/" + usuarioId + "/cursos", List.class);
       	        return ResponseEntity.ok(cursos);
       		
	       	}catch (Exception e) {
	   			// TODO: handle exception
	       		//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	       		return ResponseEntity.noContent().build();
	       		
	   		}
    	}else {
    		return ResponseEntity.notFound().build();
    	}
    }
    
    @DeleteMapping("/{usuarioId}/curso/{cursoId}")
    public ResponseEntity<Void> desinscribirUsuarioDeCurso(@PathVariable Integer usuarioId, @PathVariable Integer cursoId) {
    	
    	Usuario u = servicio.getUsuario(usuarioId);
    	if(u != null) {
    		try {
    			restTemplate.delete(URL_INSCRIPCIONES + "/usuario/" + usuarioId + "/curso/" + cursoId);
    	        	return ResponseEntity.ok().build();
    		}catch (Exception e) {
	   			// TODO: handle exception
	       		//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	       		return ResponseEntity.noContent().build();
	       		
	   		}
    	}else {
    		return ResponseEntity.notFound().build();
    	}
    	
    }
	
	@GetMapping("")
	public UsuarioDTO getUsuarioDTO(@RequestParam (defaultValue = "1") Integer p){
		return servicio.getUsuariosDTO (p-1);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable Integer id){
		Usuario u = servicio.getUsuario(id);
		if(u != null) {
			return ResponseEntity.ok(u);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Integer id){
		if(servicio.eliminarUsuario(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/setrole/{id}")
	public ResponseEntity <?> setRoleUsuario(@PathVariable Integer id, @RequestBody Usuario modificar){
		Usuario u = servicio.getUsuario(id);
		
		if(u != null ) {
			if(modificar.getRole() != null) {
					
					u.setRole(modificar.getRole());
					System.out.println("Roole: " + modificar.getRole());
					System.out.println("Usuario: " + u);
					servicio.modificarUsuarioRole(u);
					return ResponseEntity.status(HttpStatus.OK).body("El role ha cambiado con exito!!");
			}
			return ResponseEntity.unprocessableEntity().build();
			}	
			
		
		return ResponseEntity.notFound().build();
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> modificarAlumno(@PathVariable Integer id, @RequestBody Usuario modificar){
		Usuario p = servicio.getUsuario(id);
		if(p != null) {
			modificar.setId(id);
			
			if(!modificar.getNombre().isBlank() && !modificar.getApellido().isBlank()
					&& !modificar.getEmail().isBlank() 
				    && modificar.getFechaNacimiento() != null
					&& modificar.getMovil() != null ) {
				
				servicio.modificarUsuario(modificar);
				return ResponseEntity.ok(modificar);
			}
			
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
	
	
	
	
	
	
}
