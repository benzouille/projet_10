package fr.biblioc.bibliocreservation;

import fr.biblioc.bibliocreservation.web.controller.automation.MailController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Classe main du microservice reservation
 */
@EnableConfigurationProperties
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("fr.biblioc")
@EnableScheduling
public class BibliocReservationApplication {

	@Autowired
	private MailController mailController;

	public static void main(String[] args) {
		SpringApplication.run(BibliocReservationApplication.class, args);
	}

	/**
	 * Automatisation journalier du job
	 * @throws Exception -
	 */
	//1 fois par jour a 1h01
	//@Scheduled(cron = "0 1 1 * * ?")
	//toutes les 120 secondes
	@Scheduled(fixedRate = 120000)
	public void perform() throws Exception
	{
		System.out.println("scheduled run");
		mailController.inspectionDelais();
	}

}
