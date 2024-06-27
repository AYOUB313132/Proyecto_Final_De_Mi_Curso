package com.zabalburu.blog.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zabalburu.blog.dto.BlogDTO;
import com.zabalburu.blog.model.Blog;
import com.zabalburu.blog.model.Categoria;
import com.zabalburu.blog.repository.BlogRepo;
import com.zabalburu.blog.repository.CategoriaRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Servicio {
	
	private final CategoriaRepo categoriaRepo;
	
	private final BlogRepo blogRepo;
	
	public BlogDTO getBlogDTO(Integer numPagina){
		PageRequest pg = PageRequest.of(numPagina, 10);
		Page<Blog> page = blogRepo.findAll(pg);
		return new BlogDTO(numPagina + 1, page.getTotalPages(), page.getContent());
	}
	
	public List<Blog> getBloges() {
        return blogRepo.findAll(Sort.by("fecha"));
    }
	
	public List<Blog> getBlogsByCategoriaId(Integer categoriaId) {
        Optional<Categoria> optionalCategoria = categoriaRepo.findById(categoriaId);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            return categoria.getBlogs();
        } else {
            return new ArrayList<>(); // O puedes lanzar una excepción si prefieres
        }
    }
	
	public Blog getBlog(Integer id) {
		return blogRepo.findById(id).orElse(null);
	}
	
	public Blog añadirBlog(Blog nuevo) throws Exception {
		return blogRepo.save(nuevo);
	}
	
	public boolean eliminarBlog(Integer id) {
		Blog b = getBlog(id);
		if(b != null) {
			blogRepo.delete(b);
			return true;
		}
		return false;
	}
	
	public Blog modificarBlog(Blog modificar) {
			return blogRepo.save(modificar);
	}
	
	
	/* ============== Categorias  ==============*/
	
	
	public List<Categoria> getCategorias() {
        return categoriaRepo.findAll(Sort.by("nombre"));
    }
	
	public Categoria getCategoria(Integer id) {
		return categoriaRepo.findById(id).orElse(null);
	}
	
	public Categoria añadirCategoria(Categoria nuevo) throws Exception {
		Optional<Categoria> exist = categoriaRepo.findByNombre(nuevo.getNombre());
		if(exist.isEmpty()) {
			return categoriaRepo.save(nuevo);
		}
		return null;
	}
	
	public boolean eliminarCategoria(Integer id) {
		Categoria p = getCategoria(id);
		if(p != null) {
			categoriaRepo.delete(p);
			return true;
		}
		return false;
	}
	
	public Categoria modificarCategoria(Categoria modificar) {
			return categoriaRepo.save(modificar);
	}
}
