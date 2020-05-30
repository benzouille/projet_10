package fr.biblioc.bibliocbibliotheque.web.controller;

import fr.biblioc.bibliocbibliotheque.dao.AuteurDao;
import fr.biblioc.bibliocbibliotheque.dto.AuteurDto;
import fr.biblioc.bibliocbibliotheque.mapper.AuteurMapper;
import fr.biblioc.bibliocbibliotheque.model.Auteur;
import fr.biblioc.bibliocbibliotheque.model.Genre;
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
 * Controller de la classe {@link Auteur}
 */
@RestController
public class AuteurController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    AuteurDao auteurDao;

    @Autowired
    AuteurMapper auteurMapper;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Auteur> auteurs = auteurDao.findAll();

        if(auteurs.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de tous les auteurs
     * @return liste d'auteurDtos
     */
    @GetMapping(value = "/Auteurs")
    public List<AuteurDto> listeDesAuteurs(){

        List<Auteur> auteurs = auteurDao.findAll();
        List<AuteurDto> auteurDtos = new ArrayList<>();
        for (Auteur auteur : auteurs){
            auteurDtos.add(auteurMapper.auteurToAuteurDto(auteur));
        }

        if(auteurDtos.isEmpty()){
            throw new ObjectNotFoundException("Aucun auteur n'a été trouvé");
        }

        return auteurDtos;

    }

    /**
     * Récuperer un auteurDto par son id
     * @param id int
     * @return bean {@link AuteurDto}
     */
    @GetMapping( value = "/Auteurs/{id}")
    public AuteurDto recupererUnAuteur(@PathVariable int id) {

        Optional<Auteur> auteur = auteurDao.findById(id);
        AuteurDto auteurDto = null;

        if(auteur.isPresent()){
            auteurDto = auteurMapper.auteurToAuteurDto(auteur.get());
            log.info(auteurDto.toString());
        }
        return auteurDto;
    }

    /**
     * Ajouter un auteur
     * @param auteur bean {@link Auteur}
     * @return ResponseEntity<Auteur> renvoi un http status.
     */
    @PostMapping (value = "/Auteurs")
    public ResponseEntity<Auteur> addAuteur(Auteur auteur){

        Auteur newAuteur = auteurDao.save(auteur);

        if(newAuteur == null) throw new ErrorAddException("Impossible d'ajouter cet auteur");

        return new ResponseEntity<Auteur>(auteur, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour un auteur existant.
     * @param auteur bean {@link Auteur}
     **/
    @PutMapping(value = "/Auteurs")
    public void updateAuteur(@RequestBody Auteur auteur) {

        auteurDao.save(auteur);
    }
}
