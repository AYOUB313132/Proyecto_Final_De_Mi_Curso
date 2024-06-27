package com.zabalburu.controller;

import java.io.IOException;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.zabalburu.dto.RequestResponse;
import com.zabalburu.model.Usuario;
import com.zabalburu.repository.UsuarioRepo;
import com.zabalburu.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	private static final String UPLOAD_DIR = "/imagenesUsuarios";
	private final AuthService authService;
	private final UsuarioRepo repo;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RequestResponse register) throws IOException {
		
		 Optional <Usuario> check = repo.findByEmail(register.getEmail());
		 
		if(check.isEmpty()) {	
			if(!register.getApellido().isBlank() && !register.getNombre().isBlank() 
					&& !register.getEmail().isBlank() && !register.getContraseña().isBlank()
					 ) {
				//servicio.añadirUsuario(register);
				
				return ResponseEntity.status(HttpStatus.SC_CREATED).body(authService.register(register));
				
			}
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.status(HttpStatus.SC_NOT_ACCEPTABLE).body("User already exist with this email");
		
	}
	
	
	
	
	@PostMapping("/login")
	public ResponseEntity<RequestResponse> authenticate(@RequestBody RequestResponse auth) {
		
		return ResponseEntity.ok(authService.authenticate(auth));
	}
	
	
	
	@PostMapping("/refresh")
	public ResponseEntity<RequestResponse> refreshToken(@RequestBody RequestResponse refresh) {
		
		return ResponseEntity.ok(authService.refreshToken(refresh));
	}
}
