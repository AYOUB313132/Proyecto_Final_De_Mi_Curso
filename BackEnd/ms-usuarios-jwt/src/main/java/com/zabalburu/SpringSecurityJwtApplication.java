package com.zabalburu;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zabalburu.model.Role;
import com.zabalburu.model.Usuario;
import com.zabalburu.repository.UsuarioRepo;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringSecurityJwtApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepo repo;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Override
	public void run(String... args) throws Exception {
		List<Usuario> adminCompt = repo.findByRole(Role.ADMIN);
		if (adminCompt == null || adminCompt.size()<= 0) {
			Usuario admin = new Usuario();
			
			admin.setNombre("hassen");
			admin.setApellido("chetti");
			admin.setEmail("hassen@gmail.com");
			admin.setRole(Role.ADMIN);
			admin.setContraseña(new BCryptPasswordEncoder().encode("1234"));
			
			repo.save(admin);
		}
		
		 // Ruta donde deseas crear la carpeta en el servidor
        String folderPath = "/imagenesUsuarios";
        
        // Crear la carpeta si no existe
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // Crear la carpeta y cualquier directorio padre si no existen
            System.out.println("Carpeta creada con éxito en: " + folderPath);
        } else {
            System.out.println("La carpeta ya existe en: " + folderPath);
        }
		
	}
	
	/*
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/auth/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
			}
		};
	}
*/
}
