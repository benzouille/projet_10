package fr.biblioc.bibliocreservation.web.controller;

import fr.biblioc.bibliocreservation.dao.ExemplaireDao;
import fr.biblioc.bibliocreservation.dto.ExemplaireDto;
import fr.biblioc.bibliocreservation.mapper.ExemplaireMapper;
import fr.biblioc.bibliocreservation.model.Exemplaire;
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
 * Controller de la classe {@link Exemplaire}
 */
@RestController
public class ExemplaireController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    ExemplaireDao exemplaireDao;

    @Autowired
    ExemplaireMapper exemplaireMapper;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Exemplaire> exemplaires = exemplaireDao.findAll();

        if(exemplaires.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de tous les exemplaires
     * @return liste de {@link Exemplaire}
     */
    @GetMapping(value = "/Exemplaires")
    public List<ExemplaireDto> listeDesExemplaires(){

        List<Exemplaire> exemplaires = exemplaireDao.findAll();
        return getExemplaireDtos(exemplaires);
    }

    /**
     * Verifier l'existence un exemplaire par son id
     * @param id int
     * @return bean {@link Exemplaire}
     */
    @GetMapping( value = "/Exemplaires/existe/{id}")
    public boolean isExemplaire(@PathVariable int id) {
        return exemplaireDao.existsById_exemplaire(id);
    }

    /**
     * Retourne le nombre d'exemplaire présent du livre dans cette bibliothèque
     * @param id_livre int
     * @param id_biblio int
     * @return int nbre exemplaire
     */
    @GetMapping( value = "/Exemplaires/count/{id_livre}/{id_biblio}")
    public int exemplaireCount(@PathVariable("id_livre") int id_livre,@PathVariable("id_biblio") int id_biblio){
        return exemplaireDao.nbreExemplaire(id_livre, id_biblio);
    }

    /**
     * Récuperer un exemplaire par son id
     * @param id int
     * @return bean {@link Exemplaire}
     */
    @GetMapping( value = "/Exemplaires/{id}")
    public ExemplaireDto recupererUnExemplaire(@PathVariable int id) {

        Optional<Exemplaire> exemplaire = exemplaireDao.findById(id);
        return getExemplaireDto(exemplaire);
    }

    /**
     * Récuperer un exemplaire par son id
     * @param id int
     * @return bean {@link Exemplaire}
     */
    @GetMapping( value = "/Exemplaires-livre/{id}")
    public List<ExemplaireDto> recupererExemplairesByIdLivre(@PathVariable int id) {

        List<Exemplaire> exemplaires = exemplaireDao.findAllById_livre(id);
        return getExemplaireDtos(exemplaires);
    }

    /**
     * Récuperer un exemplaire par son id
     * @param id int
     * @return bean {@link Exemplaire}
     */
    @GetMapping( value = "/Exemplaires-livre-dispo/{id}")
    public List<ExemplaireDto> recupererExemplairesByIdLivreDispo(@PathVariable int id) {

        List<Exemplaire> exemplaires = exemplaireDao.findAllById_livre(id);
        List<Exemplaire> exemplairesDispo = new ArrayList<>();
        for (Exemplaire exemplaire : exemplaires){
            if(exemplaire.isDisponible()){
                exemplairesDispo.add(exemplaire);
            }
        }
        return getExemplaireDtos(exemplairesDispo);
    }

    @GetMapping( value = "/Exemplaires-livre")
    public List<ExemplaireDto> multiCrit(String multicrit) {

        log.info(multicrit);
        Integer id_auteur = 0;
        Integer id_genre = 0;
        Integer id_biblio = 0;

        List<String> output = Arrays.asList(multicrit.split("_"));
        for (int i = 0; i < output.size(); i++) {
            if (output.get(i).equals("idAuteur")) {
                id_auteur = Integer.parseInt(output.get(i + 1));
                log.info("id_auteur : " + id_auteur);
            } else if (output.get(i).equals("idGenre")) {
                id_genre = Integer.parseInt(output.get(i + 1));
                log.info("id_genre : " + id_genre);
            } else if (output.get(i).equals("idBiblio")) {
                id_biblio = Integer.parseInt(output.get(i + 1));
                log.info("id_biblio : " + id_biblio);
            }
        }

        List<Exemplaire> exemplaires = exemplaireDao.multiCrit(id_auteur, id_genre, id_biblio);
        return getExemplaireDtos(exemplaires);

    }

    /**
     * Ajouter un exemplaire
     * @param exemplaire bean {@link Exemplaire}
     * @return ResponseEntity Exemplaire  renvoi un http status.
     */
    @PostMapping(value = "/Exemplaires")
    public ResponseEntity<Exemplaire> addExemplaire(Exemplaire exemplaire){

        Exemplaire newExemplaire = exemplaireDao.save(exemplaire);

        if(newExemplaire == null) throw new ErrorAddException("Impossible d'ajouter cet exemplaire");

        return new ResponseEntity<Exemplaire>(exemplaire, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour un exemplaire existant.
     * @param exemplaire bean {@link Exemplaire}
     **/
    @PutMapping(value = "/Exemplaires")
    public void updateExemplaire(@RequestBody Exemplaire exemplaire) {

        exemplaireDao.save(exemplaire);
    }

    //------------------------- METHODE INTERNE-------------------------

    /**
     * transforme un objet exemplaire en exemplaireDto
     * @param exemplaire ENTITY
     * @return exemplaireDto DTO
     */
    private ExemplaireDto getExemplaireDto(Optional<Exemplaire> exemplaire) {
        ExemplaireDto exemplaireDto = null;

        if(exemplaire.isPresent()) {
            exemplaireDto = exemplaireMapper.exemplaireToExemplaireDto(exemplaire.get());
            log.info("ExemplaireDto : " + exemplaireDto);
        }
        return exemplaireDto;
    }

    /**
     * transforme un objet exemplaireDto en exemplaire
     * @param exemplaireDto DTO
     * @return exemplaire ENTITY
     */
    public Exemplaire getExemplaireFromDto(ExemplaireDto exemplaireDto) {
        Exemplaire exemplaire = exemplaireMapper.exemplaireDtoToExemplaire(exemplaireDto);
            log.info("Exemplaire : " + exemplaire);
        return exemplaire;
    }

    /**
     * transforme une list d'objet exemplaire en exemplaireDto
     * @param exemplaires ENTITY
     * @return exemplairesDto DTO
     */
    private List<ExemplaireDto> getExemplaireDtos(List<Exemplaire> exemplaires) {
        List<ExemplaireDto> exemplairesDto = new ArrayList<>();

        if(!exemplaires.isEmpty()){
            for (Exemplaire exemplaire : exemplaires){
                exemplairesDto.add(exemplaireMapper.exemplaireToExemplaireDto(exemplaire));
            }
        }

        log.info("List<ExemplaireDto> : " + exemplairesDto);
        return exemplairesDto;
    }
}
