package com.zabalburu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zabalburu.model.Role;
import com.zabalburu.model.Usuario;


public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

	Optional <Usuario> findByEmail(String email);
	
	List <Usuario> findByRole(Role role);
	
	Optional<Usuario> findByRoleAndId(Role role, Integer id);
}
