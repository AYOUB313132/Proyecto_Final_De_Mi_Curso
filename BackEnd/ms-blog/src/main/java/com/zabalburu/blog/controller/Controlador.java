package com.zabalburu.blog.controller;

import java.net.URI;
import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zabalburu.blog.model.Blog;
import com.zabalburu.blog.model.Categoria;
import com.zabalburu.blog.servicio.Servicio;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/blogs")
@RequiredArgsConstructor
public class Controlador {
	
	private final Servicio servicio;
	
	@GetMapping("")
    public List<Blog> getBlogs() {
       return servicio.getBloges();
    }
	
	@GetMapping("/categoriasblog/{categoriaId}")
    public ResponseEntity<List<Blog>> getBlogsByCategoria(@PathVariable Integer categoriaId) {
        List<Blog> blogs = servicio.getBlogsByCategoriaId(categoriaId);
        if (!blogs.isEmpty()) {
            return ResponseEntity.ok(blogs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Blog> getBlog(@PathVariable Integer id){
		Blog p = servicio.getBlog(id);
		if(p != null) {
			return ResponseEntity.ok(p);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("")
	public ResponseEntity<?> añadirUsuario(@RequestBody Blog nuevo) throws Exception{
		if(!nuevo.getTitulo().isBlank() && !nuevo.getParrafo().isBlank() && nuevo.getCategoria() != null
				&& nuevo.getFecha() != null && !nuevo.getDescripcion().isEmpty()) { 
			Categoria exist = servicio.getCategoria(nuevo.getCategoria().getId());
			if(exist != null) {
				servicio.añadirBlog(nuevo);
				return ResponseEntity.created(new URI("Blogs/" + nuevo.getId())).body(nuevo);
			}else {
				return ResponseEntity.status(HttpStatus.SC_NOT_ACCEPTABLE).body("El categoria no existe");
			}
			
				
			
		}
		return ResponseEntity.unprocessableEntity().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Blog> eliminarBlog(@PathVariable Integer id){
		if(servicio.eliminarBlog(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Blog> modificarBlog(@PathVariable Integer id, @RequestBody Blog modificar){
		Blog b = servicio.getBlog(id);
		if(b != null) {
			modificar.setId(id);
			
			if(!modificar.getTitulo().isBlank() && !modificar.getParrafo().isBlank()
					&& modificar.getFecha() != null && modificar.getCategoria() != null && !modificar.getDescripcion().isEmpty()) {
				
				servicio.modificarBlog(modificar);
				return ResponseEntity.ok(modificar);
			}
			
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
	/* ================= Categorias ================= */
	
	@GetMapping("/categorias")
    public List<Categoria> getCategorias() {
       return servicio.getCategorias();
    }
	
	@GetMapping("/categorias/{id}")
	public ResponseEntity<Categoria> getCategoria(@PathVariable Integer id){
		Categoria p = servicio.getCategoria(id);
		if(p != null) {
			return ResponseEntity.ok(p);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/categorias")
	public ResponseEntity<?> añadirUsuario(@RequestBody Categoria nuevo) throws Exception{
		if(!nuevo.getNombre().isBlank() ) { 
			
			if(servicio.añadirCategoria(nuevo) != null) {
				
				return ResponseEntity.created(new URI("Categorias/" + nuevo.getId())).body(nuevo);
			}else{
				return ResponseEntity.status(HttpStatus.SC_CONFLICT).body("El Categoria con este Nombre:" + nuevo.getNombre() + " es existe!!");
			}
		}
		return ResponseEntity.unprocessableEntity().build();
	}
	
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<Categoria> eliminarCategoria(@PathVariable Integer id){
		if(servicio.eliminarCategoria(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/categorias/{id}")
	public ResponseEntity<Categoria> modificarAlumno(@PathVariable Integer id, @RequestBody Categoria modificar){
		Categoria c = servicio.getCategoria(id);
		if(c != null) {
			modificar.setId(id);
			
			if(!modificar.getNombre().isBlank() ) {
				
				servicio.modificarCategoria(modificar);
				return ResponseEntity.ok(modificar);
			}
			
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
}
