package fr.biblioc.bibliocauthentification.web.controller;

import fr.biblioc.bibliocauthentification.dao.RoleDao;
import fr.biblioc.bibliocauthentification.model.Role;
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

import java.util.List;
import java.util.Optional;

/**
 * Controller de la classe {@link Role}
 */
@RestController
public class RoleController implements HealthIndicator {
    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    RoleDao roleDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Role> roles = roleDao.findAll();

        if(roles.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de tous les roles
     * @return liste de {@link Role}
     */
    @GetMapping(value = "/Roles")
    public List<Role> listeDesRoles(){

        List<Role> roles = roleDao.findAll();

        if(roles.isEmpty()){
            throw new ObjectNotFoundException("Aucun role n'a été trouvée");
        }

        log.info("Récupération de la liste des roles");

        return roles;

    }

    /**
     * Récuperer un role par son id
     * @param id int
     * @return bean {@link Role}
     */
    @GetMapping( value = "/Roles/{id}")
    public Optional<Role> recupererUnRole(@PathVariable int id) {

        Optional<Role> role = roleDao.findById(id);

        if(!role.isPresent())  throw new ObjectNotFoundException("Le role correspondant à l'id " + id + " n'existe pas");

        return role;
    }

    /**
     * Ajouter un role
     * @param role bean {@link Role}
     * @return ResponseEntity Role renvoi un http status.
     */
    @PostMapping(value = "/Roles")
    public ResponseEntity<Role> addRole(Role role){

        Role newRole = roleDao.save(role);

        if(newRole == null) throw new ErrorAddException("Impossible d'ajouter ce role");

        return new ResponseEntity<Role>(role, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour un role existant.
     * @param role bean {@link Role}
     **/
    @PutMapping(value = "/Roles")
    public void updateRole(@RequestBody Role role) {

        roleDao.save(role);
    }
}
