package com.zabalburu.inscripcion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ServicioInscripcionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioInscripcionesApplication.class, args);
	}
	
	 	@Bean
	    @LoadBalanced
	    RestTemplate restTemplate() {
			return new RestTemplate();
		}

}
