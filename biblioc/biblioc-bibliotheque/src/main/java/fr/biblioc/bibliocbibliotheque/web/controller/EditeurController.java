package fr.biblioc.bibliocbibliotheque.web.controller;

import fr.biblioc.bibliocbibliotheque.dao.EditeurDao;
import fr.biblioc.bibliocbibliotheque.model.Editeur;
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

import java.util.List;
import java.util.Optional;

/**
 * Controller de la classe {@link Editeur}
 */
@RestController
public class EditeurController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    EditeurDao editeurDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Editeur> editeurs = editeurDao.findAll();

        if(editeurs.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de tous les editeurs
     * @return liste de {@link Editeur}
     */
    @GetMapping(value = "/Editeurs")
    public List<Editeur> listeDesEditeurs(){

        List<Editeur> editeurs = editeurDao.findAll();

        if(editeurs.isEmpty()){
            throw new ObjectNotFoundException("Aucun editeur n'a été trouvé");
        }

        log.info("Récupération de la liste des editeurs");

        return editeurs;

    }

    /**
     * Récuperer un editeur par son id
     * @param id int
     * @return bean {@link Editeur}
     */
    @GetMapping( value = "/Editeurs/{id}")
    public Optional<Editeur> recupererUnEditeur(@PathVariable int id) {

        Optional<Editeur> editeur = editeurDao.findById(id);

        if(!editeur.isPresent())  throw new ObjectNotFoundException("L'editeur correspondant à l'id " + id + " n'existe pas");

        return editeur;
    }

    /**
     * Ajouter un editeur
     * @param editeur bean {@link Editeur}
     * @return ResponseEntity<Editeur> renvoi un http status.
     */
    @PostMapping(value = "/Editeurs")
    public ResponseEntity<Editeur> addEditeur(Editeur editeur){

        Editeur newEditeur = editeurDao.save(editeur);

        if(newEditeur == null) throw new ErrorAddException("Impossible d'ajouter cet editeur");

        return new ResponseEntity<Editeur>(editeur, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour un editeur existant.
     * @param editeur bean {@link Editeur}
     **/
    @PutMapping(value = "/Editeurs")
    public void updateEditeur(@RequestBody Editeur editeur) {

        editeurDao.save(editeur);
    }
}
