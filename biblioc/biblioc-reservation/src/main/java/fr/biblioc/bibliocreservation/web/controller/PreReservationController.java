package fr.biblioc.bibliocreservation.web.controller;

import fr.biblioc.bibliocreservation.dao.PreReservationDao;
import fr.biblioc.bibliocreservation.dto.PreReservationDto;
import fr.biblioc.bibliocreservation.mapper.PreReservationMapper;
import fr.biblioc.bibliocreservation.model.PreReservation;
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
import java.util.List;
import java.util.Optional;

/**
 * Controller de la classe {@link PreReservation}
 */
@RestController
public class PreReservationController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    PreReservationDao preReservationDao;

    @Autowired
    PreReservationMapper preReservationMapper;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<PreReservation> preReservations = preReservationDao.findAll();

        if(preReservations.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de toutes les preReservations
     * @return liste de {@link PreReservation}
     */
    @GetMapping(value = "/PreReservations")
    public List<PreReservationDto> listeDesPreReservations(){

        List<PreReservation> preReservations = preReservationDao.findAll();
        return getPreReservationDtos(preReservations);
    }

    /**
     * Récuperer un preReservation par son id
     * @param id int
     * @return bean {@link PreReservation}
     */
    @GetMapping( value = "/PreReservations/{id}")
    public PreReservationDto recupererUnPreReservation(@PathVariable int id) {

        Optional<PreReservation> preReservation = preReservationDao.findById(id);
        return getPreReservationDto(preReservation);
    }

    /**
     * Ajouter un preReservation
     * @param preReservation bean {@link PreReservation}
     * @return ResponseEntity PreReservation  renvoi un http status.
     */
    @PostMapping(value = "/PreReservations")
    public ResponseEntity<PreReservation> addPreReservation(PreReservation preReservation){

        PreReservation newPreReservation = preReservationDao.save(preReservation);

        if(newPreReservation == null) throw new ErrorAddException("Impossible d'ajouter cet preReservation");

        return new ResponseEntity<PreReservation>(preReservation, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour un preReservation existant.
     * @param preReservation bean {@link PreReservation}
     **/
    @PutMapping(value = "/PreReservations")
    public void updatePreReservation(@RequestBody PreReservation preReservation) {

        preReservationDao.save(preReservation);
    }

    //------------------------- METHODE INTERNE-------------------------

    /**
     * transforme un objet preReservation en preReservationDto
     * @param preReservation ENTITY
     * @return preReservationDto DTO
     */
    private PreReservationDto getPreReservationDto(Optional<PreReservation> preReservation) {
        PreReservationDto preReservationDto = null;

        if(preReservation.isPresent()) {
            preReservationDto = preReservationMapper.preReservationToPreReservationDto(preReservation.get());
            log.info("PreReservationDto : " + preReservationDto);
        }
        return preReservationDto;
    }

    /**
     * transforme une list d'objet preReservation en preReservationDto
     * @param preReservations ENTITY
     * @return preReservationsDto DTO
     */
    private List<PreReservationDto> getPreReservationDtos(List<PreReservation> preReservations) {
        List<PreReservationDto> preReservationsDto = new ArrayList<>();

        if(!preReservations.isEmpty()){
            for (PreReservation preReservation : preReservations){
                preReservationsDto.add(preReservationMapper.preReservationToPreReservationDto(preReservation));
            }
        }

        log.info("List<PreReservationDto> : " + preReservationsDto);
        return preReservationsDto;
    }
}
