package fr.biblioc.utilisateur.web.controller;

import fr.biblioc.utilisateur.dao.UtilisateurDao;
import fr.biblioc.utilisateur.dto.UtilisateurDto;
import fr.biblioc.utilisateur.mapper.UtilisateurMapper;
import fr.biblioc.utilisateur.model.Utilisateur;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller de la classe {@link Utilisateur}
 */
@RestController
public class UtilisateurController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    UtilisateurDao utilisateurDao;

    @Autowired
    UtilisateurMapper utilisateurMapper;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Utilisateur> utilisateurs = utilisateurDao.findAll();

        if(utilisateurs.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de tous les utilisateurs
     * @return liste {@link Utilisateur}
     */
    @GetMapping(value = "/Utilisateurs")
    public List<UtilisateurDto> listeDesUtilisateurs(){

        List<Utilisateur> utilisateurs = utilisateurDao.findAll();
        List<UtilisateurDto> utilisateursDto = new ArrayList<>();

        for(Utilisateur utilisateur : utilisateurs){
            utilisateursDto.add(utilisateurMapper.utilisateurToUtilisateurDto(utilisateur));
        }

        if(utilisateurs.isEmpty()){
            throw new ObjectNotFoundException("Aucun utilisateur n'a été trouvée");
        }

        log.info("Récupération de la liste des utilisateursDto");

        return utilisateursDto;

    }

    /**
     * Récuperer le dernier id_utilisateur
     * @return int id_utilisateur
     */
    @GetMapping( value = "/Utilisateurs-last")
    public int recupererLeDernierUtilisateur() {

        int id_utilisateur = utilisateurDao.findLastId_compte();

        if(id_utilisateur == 0)  throw new ObjectNotFoundException("L'utilisateur correspondant à l'id " + id_utilisateur + " n'existe pas");

        return id_utilisateur;
    }

    /**
     * Récuperer un utilisateur par son id
     * @param id int
     * @return bean {@link Utilisateur}
     */
    @GetMapping( value = "/Utilisateurs/{id}")
    public Optional<Utilisateur> recupererUnUtilisateur(@PathVariable int id) {

        Optional<Utilisateur> utilisateur = utilisateurDao.findById(id);

        if(!utilisateur.isPresent())  throw new ObjectNotFoundException("L'utilisateur correspondant à l'id " + id + " n'existe pas");

        return utilisateur;
    }

    /**
     * Ajouter un utilisateur
     * @param utilisateur bean {@link Utilisateur}
     * @return ResponseEntity<Utilisateur> renvoi un http status.
     */
    @PostMapping(value = "/Utilisateurs")
    public ResponseEntity<Utilisateur> addUtilisateur(@RequestBody Utilisateur utilisateur){

        System.out.println("user qui pose probleme" + utilisateur);
        Utilisateur newUtilisateur = utilisateurDao.save(utilisateur);

        if(newUtilisateur == null) throw new ErrorAddException("Impossible d'ajouter ce utilisateur");

        return new ResponseEntity<Utilisateur>(utilisateur, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour un utilisateur existant.
     **/
    @PutMapping(value = "/Utilisateurs")
    public void updateUtilisateur(@RequestBody Utilisateur utilisateur) {

        utilisateurDao.save(utilisateur);
    }
}
