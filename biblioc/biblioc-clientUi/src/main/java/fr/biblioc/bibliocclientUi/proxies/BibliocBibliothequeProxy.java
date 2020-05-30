package fr.biblioc.bibliocclientUi.proxies;

import fr.biblioc.bibliocclientUi.beans.bibliotheque.AuteurBean;
import fr.biblioc.bibliocclientUi.beans.bibliotheque.GenreBean;
import fr.biblioc.bibliocclientUi.beans.bibliotheque.LivreBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Proxy du microservice bibliotheque.
 */
@FeignClient(name = "biblioc-bibliotheque", url = "localhost:9002")
public interface BibliocBibliothequeProxy {

    @GetMapping(value = "/Auteurs")
    List<AuteurBean> listAuteurs();

    @GetMapping(value = "/Auteurs/{id}")
    AuteurBean getAuteur(@PathVariable("id") int id);

    @GetMapping(value = "/Livres")
    List<LivreBean> listLivres();

    @GetMapping(value = "/Livres/{id}")
    LivreBean getLivre(@PathVariable("id") int id);

    @GetMapping(value = "/Genres")
    List<GenreBean> getGenres();

    @GetMapping(value = "/Recherche")
    List<LivreBean>rechercheSimple(@RequestParam("type") String type, @RequestParam("value") String value);

}
