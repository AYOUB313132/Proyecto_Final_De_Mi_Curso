package com.zabalburu.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zabalburu.blog.model.Blog;
import com.zabalburu.blog.model.Categoria;

import java.util.List;


@Repository
public interface BlogRepo extends JpaRepository<Blog, Integer> {
	List<Blog> findByCategoria(Categoria categoria);
}
