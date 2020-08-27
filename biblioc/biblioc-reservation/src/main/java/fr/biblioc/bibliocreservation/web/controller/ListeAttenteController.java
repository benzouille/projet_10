package fr.biblioc.bibliocreservation.web.controller;

import fr.biblioc.bibliocreservation.dao.ListeAttenteDao;
import fr.biblioc.bibliocreservation.dto.ListeAttenteDto;
import fr.biblioc.bibliocreservation.mapper.ListeAttenteMapper;
import fr.biblioc.bibliocreservation.model.ListeAttente;
import fr.biblioc.bibliocreservation.web.exceptions.ErrorAddException;
import fr.biblioc.bibliocreservation.web.exceptions.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller de la classe {@link ListeAttente}
 */
@RestController
public class ListeAttenteController {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    ListeAttenteDao listeAttenteDao;

    @Autowired
    ListeAttenteMapper listeAttenteMapper;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Affiche la liste de toutes les listeAttentes
     * @return liste de {@link ListeAttente}
     */
    @GetMapping(value = "/ListeAttentes")
    public List<ListeAttenteDto> listeDesListeAttentes(){

        List<ListeAttente> listeAttentes = listeAttenteDao.findAll();
        return getListeAttenteDtos(listeAttentes);
    }

    @GetMapping(value = "/listAttenteByid/{id_liste_attente}")
    public ListeAttenteDto listAttenteById_liste_attente(@PathVariable("id_liste_attente") int id_liste_attente) {

        Optional<ListeAttente> listeAttente = listeAttenteDao.findById(id_liste_attente);
        if (listeAttente.isPresent()) {
            ListeAttenteDto listeAttenteDto = getListeAttenteDto(listeAttente.get());
            return listeAttenteDto;
        }else {
            throw new ObjectNotFoundException("la liste d'attente n'existe pas.");
        }
    }

    /**
     * Récuperer un listeAttente par son livre
     * @param id_livre int
     * @return bean {@link ListeAttente}
     */
    @GetMapping( value = "/ListeAttente/{id_livre}")
    public List<ListeAttenteDto> recupererListesAttenteParLivre(@PathVariable("id_livre") int id_livre) {

        List<ListeAttente> listeAttentes = listeAttenteDao.findByIdLivre(id_livre);
        List<ListeAttenteDto> listeAttenteDtos = new ArrayList<>();
        for (ListeAttente listeAttente : listeAttentes){
            listeAttenteDtos.add(getListeAttenteDto(listeAttente));
        }

        return listeAttenteDtos;
    }

    /**
     * Récuperer une listeAttente par sa bibliotheque et son livre
     * @param id_livre int
     * @return bean {@link ListeAttente}
     */
    @GetMapping( value = "/ListeAttente/{id_livre}/{id_bibliotheque}")
    public ListeAttenteDto recupererListesAttenteParLivre(@PathVariable("id_livre") int id_livre, @PathVariable("id_bibliotheque") int id_bibliotheque) {

        ListeAttente listeAttente = listeAttenteDao.findByIdLivreAndIdBiblio(id_livre, id_bibliotheque);
        ListeAttenteDto listeAttenteDto = getListeAttenteDto(listeAttente);

        return listeAttenteDto;
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
    private ListeAttenteDto getListeAttenteDto(ListeAttente listeAttente) {
        ListeAttenteDto listeAttenteDto = null;


            listeAttenteDto = listeAttenteMapper.listeAttenteToListeAttenteDto(listeAttente);
            log.info("ListeAttenteDto : " + listeAttenteDto);

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
