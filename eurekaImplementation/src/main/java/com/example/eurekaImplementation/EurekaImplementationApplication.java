package com.example.eurekaImplementation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaImplementationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaImplementationApplication.class, args);
	}

}
