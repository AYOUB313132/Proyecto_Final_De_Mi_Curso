package org.zabalburu.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaColegioApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaColegioApplication.class, args);
	}

}
