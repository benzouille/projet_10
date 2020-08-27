package fr.biblioc.bibliocreservation.web.controller.automation.proxies;


import fr.biblioc.bibliocreservation.web.controller.automation.model.UtilisateurBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Proxy du microservice utilisateur.
 */
@FeignClient(name = "biblioc-utilisateur", url = "localhost:9004")
public interface BibliocUtilisateurProxy {

    @GetMapping(value = "/Utilisateurs")
    List<UtilisateurBean> listUtilisateurs();

    @GetMapping( value = "/Utilisateurs-last")
    int recupererLeDernierUtilisateur();

    @GetMapping(value = "/Utilisateurs/{id}")
    UtilisateurBean getUtilisateur(@PathVariable("id") int id);

    @PostMapping(value = "/Utilisateurs")
    UtilisateurBean newUtilisateur(@RequestBody UtilisateurBean utilisateur);

    @PutMapping(value = "/Utilisateurs")
    UtilisateurBean updateUtilisateur(@RequestBody UtilisateurBean utilisateur);
}
