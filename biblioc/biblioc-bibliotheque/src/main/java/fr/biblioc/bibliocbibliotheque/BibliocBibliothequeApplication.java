package fr.biblioc.bibliocbibliotheque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Classe main du microservice bibliotheque
 */
@EnableConfigurationProperties
@EnableDiscoveryClient
@SpringBootApplication
public class BibliocBibliothequeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliocBibliothequeApplication.class, args);
	}

}
