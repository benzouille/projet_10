package fr.biblioc.bibliocauthentification.web.controller;

import fr.biblioc.bibliocauthentification.dao.CompteDao;
import fr.biblioc.bibliocauthentification.dto.CompteDto;
import fr.biblioc.bibliocauthentification.mapper.CompteMapper;
import fr.biblioc.bibliocauthentification.model.Compte;
import fr.biblioc.bibliocauthentification.web.exceptions.ErrorAddException;
import fr.biblioc.bibliocauthentification.web.exceptions.ObjectNotFoundException;
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
 * Controller de la classe {@link Compte}
 */
@RestController
public class CompteController implements HealthIndicator {
    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    CompteDao compteDao;

    @Autowired
    CompteMapper compteMapper;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Compte> comptes = compteDao.findAll();

        if(comptes.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de tous les comptes
     * @return liste {@link Compte}
     */
    @GetMapping(value = "/Comptes")
    public List<CompteDto> listeDesComptes(){

        List<Compte> comptes = compteDao.findAll();
        List<CompteDto> comptesDto = new ArrayList<>();

        for (Compte compte : comptes){
            comptesDto.add(compteMapper.compteToCompteDto(compte));
        }

        if(comptes.isEmpty()){
            throw new ObjectNotFoundException("Aucun compte n'a été trouvée");
        }

        log.info("Récupération de la liste des comptesDto");

        return comptesDto;

    }

    /**
     * Verifier l'existence un compte par son id
     * @param id int
     * @return bean {@link Compte}
     */
    @GetMapping( value = "/Comptes/existe/{id}")
    public boolean isCompte(@PathVariable int id) {
        return compteDao.existsById_compte(id);
    }

    /**
     * Récuperer un compte par son id
     * @param id int
     * @return bean {@link Compte}
     */
    @GetMapping( value = "/Comptes/{id}")
    public Optional<Compte> recupererUnCompte(@PathVariable int id) {

        Optional<Compte> compte = compteDao.findById(id);

        if(!compte.isPresent())  throw new ObjectNotFoundException("L'compte correspondant à l'id " + id + " n'existe pas");

        return compte;
    }

    /**
     * Récuperer un compte par son email
     * @param email String
     * @return bean {@link Compte}
     */
    @GetMapping( value = "/Comptes_mail/{email}")
    public Compte recupererUnCompte(@PathVariable String email) {

        Compte compte = compteDao.findByEmail(email);

        return compte;
    }

    /**
     * Ajouter un compte
     * @param compte bean {@link Compte}
     * @return ResponseEntity Compte renvoi un http status.
     */
    @PostMapping(value = "/Comptes")
    public ResponseEntity<Compte> newCompte(@RequestBody Compte compte){

        Compte newCompte = compteDao.save(compte);

        if(newCompte == null) throw new ErrorAddException("Impossible d'ajouter ce compte");

        return new ResponseEntity<Compte>(compte, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour un compte existant.
     * @param compte bean {@link Compte}
     **/
    @PutMapping(value = "/Comptes")
    public void updateCompte(@RequestBody Compte compte) {
        System.out.println("compte" + compte);
        compteDao.save(compte);
    }
}