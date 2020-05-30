package fr.biblioc.utilisateur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Classe main du microservice utilisateur
 */
@EnableConfigurationProperties
@EnableDiscoveryClient
@SpringBootApplication
public class BibliocUtilisateurApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliocUtilisateurApplication.class, args);
	}

}
