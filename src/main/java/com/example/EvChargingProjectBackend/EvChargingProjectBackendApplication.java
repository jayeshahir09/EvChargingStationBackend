package com.example.EvChargingProjectBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EvChargingProjectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvChargingProjectBackendApplication.class, args);
	}

}
