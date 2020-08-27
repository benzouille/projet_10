package fr.biblioc.bibliocreservation.web.controller.automation.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Proxy du microservice bibliotheque.
 */
@FeignClient(name = "biblioc-bibliotheque", url = "localhost:9002")
public interface BibliocBibliothequeProxy {

    @GetMapping(value = "/Titre/{id}")
    String getTitreLivre(@PathVariable("id") int id);

}
