package fr.biblioc.bibliocreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Classe main du microservice reservation
 */
@EnableConfigurationProperties
@EnableDiscoveryClient
@SpringBootApplication
public class BibliocReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliocReservationApplication.class, args);
	}

}
