package fr.biblioc.utilisateur.web.controller;

import fr.biblioc.utilisateur.dao.AdresseDao;
import fr.biblioc.utilisateur.model.Adresse;
import fr.biblioc.utilisateur.web.exceptions.ErrorAddException;
import fr.biblioc.utilisateur.web.exceptions.ObjectNotFoundException;
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
 * Controller de la classe {@link Adresse}
 */
@RestController
public class AdresseController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    AdresseDao adresseDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Adresse> adresses = adresseDao.findAll();

        if(adresses.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de toutes les adresses
     * @return liste d'adresses
     */
    @GetMapping(value = "/Adresses")
    public List<Adresse> listeDesAdresses(){

        List<Adresse> adresses = adresseDao.findAll();

        if(adresses.isEmpty()){
            throw new ObjectNotFoundException("Aucune adresse n'a été trouvée");
        }

        log.info("Récupération de la liste des adresses");

        return adresses;

    }

    /**
     * Récuperer une adresse par son id
     * @param id int
     * @return bean adresse
     */
    @GetMapping( value = "/Adresses/{id}")
    public Optional<Adresse> recupererUneAdresse(@PathVariable int id) {

        Optional<Adresse> adresse = adresseDao.findById(id);

        if(!adresse.isPresent())  throw new ObjectNotFoundException("L'adresse correspondant à l'id " + id + " n'existe pas");

        return adresse;
    }

    /**
     * Ajouter un adresse
     * @param adresse bean {@link Adresse}
     * @return ResponseEntity de Adresse renvoi un http status.
     */
    @PostMapping(value = "/Adresses")
    public ResponseEntity<Adresse> addAdresse(Adresse adresse){

        Adresse newAdresse = adresseDao.save(adresse);

        if(newAdresse == null) throw new ErrorAddException("Impossible d'ajouter ce adresse");

        return new ResponseEntity<Adresse>(adresse, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour un adresse existant.
     * @param adresse bean {@link Adresse}
     **/
    @PutMapping(value = "/Adresses")
    public void updateAdresse(@RequestBody Adresse adresse) {

        adresseDao.save(adresse);
    }
}
