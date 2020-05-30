package fr.biblioc.bibliocclientUi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Classe Main du ClientUi
 */
@SpringBootApplication
@EnableFeignClients("fr.biblioc")
public class BibliocClientUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliocClientUiApplication.class, args);
	}

}
