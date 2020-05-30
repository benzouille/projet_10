package fr.biblioc.bibliocauthentification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Classe main du microservice authentification
 */
@EnableConfigurationProperties
@EnableDiscoveryClient
@SpringBootApplication
public class BibliocAuthentificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliocAuthentificationApplication.class, args);
	}

}
