package com.zabalburu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zabalburu.model.Role;
import com.zabalburu.model.Usuario;
import com.zabalburu.model.UsuarioDTO;
import com.zabalburu.repository.UsuarioRepo;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ServicioProfesores {
	
	
	private final UsuarioRepo repo;
	
	private final PasswordEncoder passwordEncoder;
	
	
	public List<Usuario> getProfesores(){
		return repo.findByRole(Role.PROFESOR);
	}
	
	
	public Usuario getProfesor(Integer id) {
		return repo.findByRoleAndId(Role.PROFESOR, id).orElse(null);
	}
	
	public Usuario getUsuario(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public boolean eliminarUsuario(Integer id) {
		Usuario u = getUsuario(id);
		if(u != null) {
			repo.delete(u);
			return true;
		}
		return false;
	}
	
	public Usuario modificarUsuario(Usuario modificar) {
		modificar.setContraseña(passwordEncoder.encode( modificar.getContraseña() ));
		return repo.save(modificar);
	}
	
	
	/* ==================== Role ==================== */
	
	
	public boolean modificarRole(Integer idUsuario, String role) {
		Usuario u = getUsuario(idUsuario);
		if(u != null) {
			if(role.equalsIgnoreCase("ADMIN")) {
				u.setRole(Role.ADMIN);
			}else if(role.equalsIgnoreCase("PROFESOR")) {
				u.setRole(Role.PROFESOR);
			}
			return true;
		}
		return false;
	}

	
	
	
}







