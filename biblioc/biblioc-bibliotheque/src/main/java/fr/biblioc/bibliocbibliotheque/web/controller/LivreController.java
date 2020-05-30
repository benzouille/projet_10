package fr.biblioc.bibliocbibliotheque.web.controller;

import fr.biblioc.bibliocbibliotheque.dao.LivreDao;
import fr.biblioc.bibliocbibliotheque.dto.LivreDto;
import fr.biblioc.bibliocbibliotheque.mapper.LivreMapper;
import fr.biblioc.bibliocbibliotheque.model.Livre;
import fr.biblioc.bibliocbibliotheque.web.exceptions.ErrorAddException;
import fr.biblioc.bibliocbibliotheque.web.exceptions.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller de la classe {@link Livre}
 */
@RestController
public class LivreController implements HealthIndicator {
    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    LivreDao livreDao;

    @Autowired
    LivreMapper livreMapper;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Livre> livres = livreDao.findAll();

        if(livres.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de tous les livres
     * @return liste de {@link Livre}
     */
    @GetMapping(value = "/Livres")
    public List<Livre> listeDesLivres(){

        List<Livre> livres = livreDao.findAll();

        if(livres.isEmpty()){
            throw new ObjectNotFoundException("Aucun livre n'a été trouvé");
        }

        log.info("Récupération de la liste des livres");

        return livres;

    }

    /**
     * Récuperer un livre par son id
     * @param id int
     * @return bean {@link Livre}
     */
    @GetMapping( value = "/Livres/{id}")
    public LivreDto recupererUnLivre(@PathVariable int id) {

        Optional<Livre> livre = livreDao.findById(id);
        LivreDto livreDto = null;

        if(livre.isPresent()){
            livreDto = livreMapper.livreToLivreDto(livre.get());
            log.info(livreDto.toString());
        }

        log.info("Récupération du livre avec l'id : "+id);

        return livreDto;
    }

    /**
     * Ajouter un livre
     * @param livre bean {@link Livre}
     * @return ResponseEntity bean {@link Livre} renvoi un http status.
     */
    @PostMapping (value = "/Livres")
    public ResponseEntity<Livre> addLivre(Livre livre){

        Livre newLivre = livreDao.save(livre);

        if(newLivre == null) throw new ErrorAddException("Impossible d'ajouter ce livre");

        return new ResponseEntity<Livre>(livre, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour un livre existant.
     * @param livre bean {@link Livre}
     */
    @PutMapping(value = "/Livres")
    public void updateLivre(@RequestBody Livre livre) {

        livreDao.save(livre);
    }
}
