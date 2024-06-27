package com.zabalburu.service;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zabalburu.dto.RequestResponse;
import com.zabalburu.model.Role;
import com.zabalburu.model.Usuario;
import com.zabalburu.repository.UsuarioRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UsuarioRepo usuarioRepo;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	
	
	public RequestResponse register(RequestResponse registration) {
		RequestResponse requestResponse = new RequestResponse();
		try {
			Usuario u = new Usuario();
			
			u.setNombre(registration.getNombre());
			u.setApellido(registration.getApellido());
			u.setEmail(registration.getEmail());
			u.setContraseña(passwordEncoder.encode( registration.getContraseña()));
			u.setFechaNacimiento(registration.getFechaNacimiento() );
			u.setGenero(registration.getGenero() );
			u.setMovil(registration.getMovil() );
			u.setPais(registration.getPais() );
			u.setProvincia(registration.getProvincia() );
			u.setCiudad(registration.getCiudad() );
			u.setCodigoPostal(registration.getCodigoPostal() );
			u.setDireccion(registration.getDireccion() );
			u.setImagen(registration.getImagen());
			u.setRole(Role.USER);
			Usuario usReslt =  usuarioRepo.save(u);
			if(usReslt != null && usReslt.getId() >0) {
				requestResponse.setMessage("Usuario created successfully");
				requestResponse.setStatusCode(200);
				requestResponse.setUsuario(u);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			requestResponse.setError(e.getMessage());
			requestResponse.setStatusCode(500);
		}
		return requestResponse;
	}
	
	
	
	public RequestResponse authenticate (RequestResponse auth) {
		RequestResponse response = new RequestResponse();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getContraseña()));
			var usuario = usuarioRepo.findByEmail(auth.getEmail()).orElseThrow();
			System.out.println("Usuario es: " + usuario);
			var jwt = jwtService.generateToken(usuario);
			var refreshToken = jwtService.generetRefreshToken(new HashMap<>(), usuario);
			
			response.setStatusCode(200);
			response.setToken(jwt);
			response.setRefreshToken(refreshToken);
			response.setExperationTime("24H");
			response.setUsuario(usuario);
			response.setMessage("Successfully Sign In");
		}catch (Exception e) {
			// TODO: handle exceptiont
			response.setError(e.getMessage());
			response.setStatusCode(500);
			
			//throw new BadCredentialsException("Incorrect username or password" + response);
			
		}
		return response;
	}
	
	
	public RequestResponse refreshToken(RequestResponse refresh) {
		RequestResponse refreshResponse = new RequestResponse();
		
		String ourEmail = jwtService.extractUsuario(refresh.getToken());
		Usuario usuario = usuarioRepo.findByEmail(ourEmail).orElseThrow();
		
		if(jwtService.isTokenValid(refresh.getToken(), usuario)) {
			var jwt = jwtService.generateToken(usuario);
			refreshResponse.setStatusCode(200);
			refreshResponse.setToken(jwt);
			refreshResponse.setRefreshToken(refresh.getToken());
			refreshResponse.setExperationTime("24H");
			refreshResponse.setMessage("Successfully Refresh Token");
			
		}
		
		refreshResponse.setStatusCode(500);
		
		return refreshResponse;
	}
	
	
	
	
	
	
	
	
}
