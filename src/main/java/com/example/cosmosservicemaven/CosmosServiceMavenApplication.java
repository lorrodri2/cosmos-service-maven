package com.example.cosmosservicemaven;

import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCosmosRepositories
public class CosmosServiceMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosmosServiceMavenApplication.class, args);
	}
}
