package com.zabalburu.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zabalburu.blog.model.Categoria;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {
	
	Optional <Categoria> findByNombre(String nombre);
}
