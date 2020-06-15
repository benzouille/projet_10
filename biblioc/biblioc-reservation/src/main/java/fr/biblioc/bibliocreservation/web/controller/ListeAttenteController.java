package fr.biblioc.bibliocreservation.web.controller;

import fr.biblioc.bibliocreservation.dao.ListeAttenteDao;
import fr.biblioc.bibliocreservation.dto.ListeAttenteDto;
import fr.biblioc.bibliocreservation.mapper.ListeAttenteMapper;
import fr.biblioc.bibliocreservation.model.ListeAttente;
import fr.biblioc.bibliocreservation.web.exceptions.ErrorAddException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Controller de la classe {@link ListeAttente}
 */
@RestController
public class ListeAttenteController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    ListeAttenteDao listeAttenteDao;

    @Autowired
    ListeAttenteMapper listeAttenteMapper;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<ListeAttente> listeAttentes = listeAttenteDao.findAll();

        if(listeAttentes.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de toutes les listeAttentes
     * @return liste de {@link ListeAttente}
     */
    @GetMapping(value = "/ListeAttentes")
    public List<ListeAttenteDto> listeDesListeAttentes(){

        List<ListeAttente> listeAttentes = listeAttenteDao.findAll();
        return getListeAttenteDtos(listeAttentes);
    }

    /**
     * Récuperer un listeAttente par son id
     * @param id int
     * @return bean {@link ListeAttente}
     */
    @GetMapping( value = "/ListeAttentes/{id}")
    public ListeAttenteDto recupererUnListeAttente(@PathVariable int id) {

        Optional<ListeAttente> listeAttente = listeAttenteDao.findById(id);
        return getListeAttenteDto(listeAttente);
    }

    /**
     * Ajouter un listeAttente
     * @param listeAttente bean {@link ListeAttente}
     * @return ResponseEntity ListeAttente  renvoi un http status.
     */
    @PostMapping(value = "/ListeAttentes")
    public ResponseEntity<ListeAttente> addListeAttente(ListeAttente listeAttente){

        ListeAttente newListeAttente = listeAttenteDao.save(listeAttente);

        if(newListeAttente == null) throw new ErrorAddException("Impossible d'ajouter cet listeAttente");

        return new ResponseEntity<ListeAttente>(listeAttente, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour un listeAttente existant.
     * @param listeAttente bean {@link ListeAttente}
     **/
    @PutMapping(value = "/ListeAttentes")
    public void updateListeAttente(@RequestBody ListeAttente listeAttente) {

        listeAttenteDao.save(listeAttente);
    }

    //------------------------- METHODE INTERNE-------------------------

    /**
     * transforme un objet listeAttente en listeAttenteDto
     * @param listeAttente ENTITY
     * @return listeAttenteDto DTO
     */
    private ListeAttenteDto getListeAttenteDto(Optional<ListeAttente> listeAttente) {
        ListeAttenteDto listeAttenteDto = null;

        if(listeAttente.isPresent()) {
            listeAttenteDto = listeAttenteMapper.listeAttenteToListeAttenteDto(listeAttente.get());
            log.info("ListeAttenteDto : " + listeAttenteDto);
        }
        return listeAttenteDto;
    }

    /**
     * transforme une list d'objet listeAttente en listeAttenteDto
     * @param listeAttentes ENTITY
     * @return listeAttentesDto DTO
     */
    private List<ListeAttenteDto> getListeAttenteDtos(List<ListeAttente> listeAttentes) {
        List<ListeAttenteDto> listeAttentesDto = new ArrayList<>();

        if(!listeAttentes.isEmpty()){
            for (ListeAttente listeAttente : listeAttentes){
                listeAttentesDto.add(listeAttenteMapper.listeAttenteToListeAttenteDto(listeAttente));
            }
        }

        log.info("List<ListeAttenteDto> : " + listeAttentesDto);
        return listeAttentesDto;
    }

}
