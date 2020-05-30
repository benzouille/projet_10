package fr.biblioc.bibliocclientUi.controller;

import fr.biblioc.bibliocclientUi.beans.bibliotheque.AuteurBean;
import fr.biblioc.bibliocclientUi.beans.bibliotheque.GenreBean;
import fr.biblioc.bibliocclientUi.beans.bibliotheque.LivreBean;
import fr.biblioc.bibliocclientUi.beans.reservation.BibliothequeBean;
import fr.biblioc.bibliocclientUi.beans.reservation.ExemplaireBean;
import fr.biblioc.bibliocclientUi.proxies.BibliocBibliothequeProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocReservationProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controller utilisant le proxy vers le microservice bibliotheque
 */
@Controller
public class BibliothequeController {

    @Autowired
    private BibliocBibliothequeProxy bibliothequeProxy;

    @Autowired
    private BibliocReservationProxy reservationProxy;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    @RequestMapping(value = "/recherche", method = RequestMethod.GET)
    public ModelAndView recherches(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("recherche");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request); // 1
        if (!CollectionUtils.isEmpty(flashMap)) {
            modelAndView.addObject("livres", flashMap.get("livres")); // 2
            modelAndView.addObject("erreur", flashMap.get("erreur"));
        }

        LivreBean livre = new LivreBean();
        modelAndView.addObject("livre", livre);

        AuteurBean auteur = new AuteurBean();
        modelAndView.addObject("auteur", auteur);

        List<AuteurBean> auteurs = bibliothequeProxy.listAuteurs();
        modelAndView.addObject("auteurs", auteurs);

        List<GenreBean> genres = bibliothequeProxy.getGenres();
        modelAndView.addObject("genres", genres);

        List<BibliothequeBean> biblios = reservationProxy.listBibliotheques();
        modelAndView.addObject("biblios", biblios);

        return modelAndView;
    }

    /**
     * Recherche simple
     *
     * @param titre
     * @param id_auteur
     * @param id_genre
     * @param redirectAttributes
     * @return
     */
    @PostMapping(value = "/recherche")
    public ModelAndView rechercheLivre(String titre, String id_auteur, String id_genre, RedirectAttributes redirectAttributes) {

        List<LivreBean> livres = null;

        if (titre != null) {
            livres = bibliothequeProxy.rechercheSimple("titre", titre);
        }
        if (id_auteur != null) {
            livres = bibliothequeProxy.rechercheSimple("auteur", id_auteur);
        }
        if (id_genre != null) {
            livres = bibliothequeProxy.rechercheSimple("genre", id_genre);
        }

        //le retour
        redirectAttributes.addFlashAttribute("livres", livres);

        return new ModelAndView("redirect:/recherche");
    }

    /**
     * recherche multiple
     *
     * @param id_auteur int
     * @param id_genre int
     * @param id_biblio int
     * @param redirectAttributes redirectAttribute
     * @return modelAndView
     */
    @PostMapping(value = "/recherches")
    public ModelAndView recherchesLivre(Integer id_auteur, Integer id_genre, Integer id_biblio, RedirectAttributes redirectAttributes) {
        String multicrit = "";

        if (id_auteur.equals(-1) | id_genre.equals(-1) | id_biblio.equals(-1)) {
            String erreur = "veuillez remplir les 3 champs !";
            redirectAttributes.addFlashAttribute("erreur", erreur);
        } else {
                multicrit+= "_idAuteur_" + id_auteur;
                multicrit+= "_idGenre_" + id_genre;
                multicrit+= "_idBiblio_" + id_biblio;
        }

        List<ExemplaireBean> exemplaires = reservationProxy.rechercheMulti(multicrit);
        List<LivreBean> livres = new ArrayList<>();
        for (ExemplaireBean exemplaire : exemplaires){
            livres.add(bibliothequeProxy.getLivre(exemplaire.getId_livre()));
        }
        redirectAttributes.addFlashAttribute("livres", livres);

        return new ModelAndView("redirect:/recherche");
    }

    @RequestMapping(value = "/details-auteur/{id}", method = RequestMethod.GET)
    public String ficheAuteur(@PathVariable int id, Model model) {

        AuteurBean auteur = bibliothequeProxy.getAuteur(id);
        List<LivreBean> livres = bibliothequeProxy.listLivres();

        model.addAttribute("auteur", auteur);
        model.addAttribute("livres", livres);

        return "fiche-auteur";
    }

    @RequestMapping(value = "/details-livre/{id}", method = RequestMethod.GET)
    public String ficheLivre(@PathVariable int id, Model model) {

        LivreBean livre = bibliothequeProxy.getLivre(id);
        List<ExemplaireBean> exemplaires = reservationProxy.getExemplairesByIdLivreDispo(id);
        List<BibliothequeBean> bibliotheques = reservationProxy.listBibliotheques();

        populate(bibliotheques, exemplaires);
        model.addAttribute("livre", livre);
        model.addAttribute("bibliotheques", bibliotheques);

        return "fiche-livre";
    }

    private void populate(List<BibliothequeBean> bibliotheques, List<ExemplaireBean> exemplaires){
        for (BibliothequeBean bibliotheque : bibliotheques) {
            for (ExemplaireBean exemplaire : exemplaires) {
                if (exemplaire.getBibliotheque().getid_biblio() == bibliotheque.getid_biblio()){
                    bibliotheque.addExemplaire(exemplaire);
                }
            }
        }
    }
}
