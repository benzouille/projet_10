package fr.biblioc.bibliocreservation.web.controller;

import fr.biblioc.bibliocreservation.dao.BibliothequeDao;
import fr.biblioc.bibliocreservation.dto.BibliothequeDto;
import fr.biblioc.bibliocreservation.mapper.BibliothequeMapper;
import fr.biblioc.bibliocreservation.model.Bibliotheque;
import fr.biblioc.bibliocreservation.web.exceptions.ErrorAddException;
import fr.biblioc.bibliocreservation.web.exceptions.ObjectNotFoundException;
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
 * Controller de la classe {@link Bibliotheque}
 */
@RestController
public class BibliothequeController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    BibliothequeDao bibliothequeDao;

    @Autowired
    BibliothequeMapper bibliothequeMapper;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Bibliotheque> bibliotheques = bibliothequeDao.findAll();

        if(bibliotheques.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de toutes les bibliotheques
     * @return liste de {@link Bibliotheque}
     */
    @GetMapping(value = "/Bibliotheques")
    public List<BibliothequeDto> listeDesBibliotheques(){

        List<Bibliotheque> bibliotheques = bibliothequeDao.findAll();
        List<BibliothequeDto> bibliothequesDto = new ArrayList<>();

        if(!bibliotheques.isEmpty()){
            for(Bibliotheque bibliotheque : bibliotheques){
                bibliothequesDto.add(bibliothequeMapper.bibliothequeToBibliothequeDto(bibliotheque));
            }
        }

        log.info("Récupération de la liste des DTO bibliotheques");

        return bibliothequesDto;

    }

    /**
     * Récuperer une bibliotheque par son id
     * @param id int
     * @return bean {@link Bibliotheque}
     */
    @GetMapping( value = "/Bibliotheques/{id}")
    public BibliothequeDto recupererUneBibliotheque(@PathVariable int id) {

        Optional<Bibliotheque> bibliotheque = bibliothequeDao.findById(id);
        BibliothequeDto bibliothequeDto = null;

        if(bibliotheque.isPresent()){
            bibliothequeDto = bibliothequeMapper.bibliothequeToBibliothequeDto(bibliotheque.get());
            log.info(bibliothequeDto.toString());
        }

        return bibliothequeDto;
    }

    /**
     * Ajouter une bibliotheque
     * @param bibliotheque bean {@link Bibliotheque}
     * @return ResponseEntity Bibliotheque renvoi un http status.
     */
    @PostMapping(value = "/Bibliotheques")
    public ResponseEntity<Bibliotheque> addBibliotheque(Bibliotheque bibliotheque){

        Bibliotheque newBibliotheque = bibliothequeDao.save(bibliotheque);

        if(newBibliotheque == null) throw new ErrorAddException("Impossible d'ajouter ce bibliotheque");

        return new ResponseEntity<Bibliotheque>(bibliotheque, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour un bibliotheque existant.
     * @param bibliotheque bean {@link Bibliotheque}
     **/
    @PutMapping(value = "/Bibliotheques")
    public void updateBibliotheque(@RequestBody Bibliotheque bibliotheque) {

        bibliothequeDao.save(bibliotheque);
    }
}
