package fr.biblioc.bibliocbibliotheque.web.controller;

import fr.biblioc.bibliocbibliotheque.dao.LivreDao;
import fr.biblioc.bibliocbibliotheque.dto.AuteurDto;
import fr.biblioc.bibliocbibliotheque.dto.LivreDto;
import fr.biblioc.bibliocbibliotheque.mapper.LivreMapper;
import fr.biblioc.bibliocbibliotheque.model.Livre;
import fr.biblioc.bibliocbibliotheque.web.exceptions.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller des recherches
 */
@RestController
public class RechercheController {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    LivreDao livreDao;

    @Autowired
    LivreMapper livreMapper;

    @Autowired
    AuteurController auteurController;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Requete de recherche de livre par critères simple
     * @param type string
     * @param value string
     * @return
     */
    @GetMapping(value = "/Recherche")
    List<LivreDto> rechercheSimple(String type, String value) {
        log.info("rechercheSimple : [ type : " + type + ", value : " + value + " ]");

        List<Livre> livres = null;

        if (type.equals("titre")) {
            livres = livreDao.findByTitre(value);
        } else if (type.equals("auteur")) {
            AuteurDto auteur = auteurController.recupererUnAuteur(Integer.parseInt(value));
            livres = auteur.getBibliographie();
        } else if (type.equals("genre")) {
            livres = livreDao.findById_genre(Integer.parseInt(value));
        }

        List<LivreDto> livresDto = new ArrayList<>();
        for (Livre livre : livres) {
            livresDto.add(livreMapper.livreToLivreDto(livre));
        }

        return livresDto;
    }
}
