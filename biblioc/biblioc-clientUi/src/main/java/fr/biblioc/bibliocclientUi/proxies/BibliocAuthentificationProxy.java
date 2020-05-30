package fr.biblioc.bibliocclientUi.proxies;

import fr.biblioc.bibliocclientUi.beans.authentification.CompteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Proxy du microservice authentification.
 */
@FeignClient(name = "biblioc-authentification", url = "localhost:9001")
public interface BibliocAuthentificationProxy {

    @GetMapping(value = "/Comptes")
    List<CompteBean> listComptes();

    @GetMapping(value = "/Comptes/existe/{id}")
    boolean isCompte(@PathVariable("id") int id);

    @GetMapping(value = "/Comptes/{id}")
    CompteBean getCompte(@PathVariable("id") int id);

    @GetMapping( value = "/Comptes_mail/{email}")
    CompteBean getCompte(@PathVariable("email") String email);

    @PutMapping(value = "/Comptes")
    CompteBean updateCompte(@RequestBody CompteBean compte);

    @PostMapping(value = "/Comptes")
    CompteBean newCompte(@RequestBody CompteBean compte);
}
