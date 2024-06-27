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
public class ServicioUsuarios {
	
	@Autowired
	private UsuarioRepo repo;
	
	private final PasswordEncoder passwordEncoder;
	
	public UsuarioDTO getUsuariosDTO(Integer numPagina){
		PageRequest pg = PageRequest.of(numPagina, 10);
		Page<Usuario> page = repo.findAll(pg);
		return new UsuarioDTO(numPagina + 1, page.getTotalPages(), page.getContent());
	}
	

	
	public List<Usuario> getUsuarios() {
        return repo.findAll(Sort.by("nombre"));
    }
	
	
	public List<Usuario> getProfesores(){
		return repo.findByRole(Role.PROFESOR);
	}
	
	
	public Usuario getUsuario(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	/*
	public Usuario añadirUsuario(Usuario nuevo) {
		return repo.save(nuevo);
	}*/
	
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
	
	public Usuario modificarUsuarioRole(Usuario modifRole) {
		return repo.save(modifRole);
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







