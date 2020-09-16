package fr.biblioc.bibliocreservation.web.controller;

import fr.biblioc.bibliocreservation.dao.PreReservationDao;
import fr.biblioc.bibliocreservation.dto.PreReservationDto;
import fr.biblioc.bibliocreservation.mapper.PreReservationMapper;
import fr.biblioc.bibliocreservation.model.PreReservation;
import fr.biblioc.bibliocreservation.web.exceptions.ErrorAddException;
import fr.biblioc.bibliocreservation.web.exceptions.FunctionalException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller de la classe {@link PreReservation}
 */
@RestController
public class PreReservationController {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    PreReservationDao preReservationDao;

    @Autowired
    PreReservationMapper preReservationMapper;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Affiche la liste de toutes les preReservations
     * @return liste de {@link PreReservation}
     */
    @GetMapping(value = "/PreReservations")
    public List<PreReservationDto> listeDesPreReservations() {
        List<PreReservationDto> preReservationDtoList = null;
        List<PreReservation> preReservations = getPrereservationDao();
        try {
            preReservationDtoList = getPreReservationDtos(preReservations);
        } catch (FunctionalException e) {
            e.printStackTrace();
        }

        return preReservationDtoList;
    }

    @NotNull
    protected List<PreReservation> getPrereservationDao() {
        return preReservationDao.findAll();
    }

    /**
     * Affiche la liste de toutes les preReservations d'un utilisateur
     * @return liste de {@link PreReservation}
     */
    @GetMapping(value = "/PreReservations/{id_compte}")
    public List<PreReservationDto> listPreReservationsByIdUser(@PathVariable int id_compte){
        List<PreReservationDto> preReservationDtoList = null;
        List<PreReservation> preReservations = getPrereservationDaoById_compte(id_compte);
        try {
            preReservationDtoList = getPreReservationDtos(preReservations);
        } catch (FunctionalException e) {
            e.printStackTrace();
        }

        return preReservationDtoList;
    }

    @NotNull
    protected List<PreReservation> getPrereservationDaoById_compte(int id_compte) {
        return preReservationDao.findAllById_compte(id_compte);
    }

    /**
     * Récuperer une preReservation par son id
     * @param id_preReservation int
     * @return bean {@link PreReservation}
     */
    @GetMapping( value = "/PreReservation/{id_preReservation}")
    public PreReservationDto preReservationbyId(@PathVariable int id_preReservation) {

        Optional<PreReservation> preReservation = preReservationDao.findById(id_preReservation);
        return getPreReservationDto(preReservation);
    }

    /**
     * Ajouter une preReservation
     * @param preReservation bean {@link PreReservation}
     * @return ResponseEntity PreReservation  renvoi un http status.
     */
    @PostMapping(value = "/PreReservation")
    public ResponseEntity<PreReservation> addPreReservation(@RequestBody PreReservation preReservation){
        System.out.println(preReservation.toString());


        if(preReservation == null) {
            throw new ErrorAddException("Impossible d'ajouter cette preReservation");
        } else {
            preReservationDao.save(preReservation);
        }

        return new ResponseEntity<PreReservation>(preReservation, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour un preReservation existant.
     * @param preReservation bean {@link PreReservation}
     **/
    @PutMapping(value = "/PreReservation")
    public void updatePreReservation(@RequestBody PreReservation preReservation) {

        preReservationDao.save(preReservation);
    }

    @PostMapping(value = "/Prereservation/checkAcceptationDuration")
    public void checkAcceptationDuration(){

        getExpiredMailSendPreReservation();
    }

    //------------------------- METHODE INTERNE-------------------------

    public List<PreReservation> getExpiredMailSendPreReservation(){
        List<PreReservation> preReservations = preReservationDao.findAllByNotExpired();

        List<PreReservation> preReservationsChecked = new ArrayList<>();

        for (PreReservation preReservation : preReservations){
            if(expirationDateCheck((Date) preReservation.getDatePreReservation())){
                preReservationsChecked.add(preReservation);
            }
        }
        return preReservationsChecked;
    }

    public boolean expirationDateCheck(Date date){
        LocalDate localDate = LocalDate.now().minusDays(2);

        if(date.after(Date.valueOf(localDate))){
            return false;
        } else {
            return true;
        }

    }

    /**
     * transforme un objet preReservation en preReservationDto
     * @param preReservation ENTITY
     * @return preReservationDto DTO
     */
    protected PreReservationDto getPreReservationDto(Optional<PreReservation> preReservation) {
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
    public List<PreReservationDto> getPreReservationDtos(List<PreReservation> preReservations) throws FunctionalException {
        List<PreReservationDto> preReservationsDto = new ArrayList<>();

        if ( preReservations.isEmpty() ) {
            throw new FunctionalException("la liste est vide");
        } else {
            for (PreReservation preReservation : preReservations) {
                preReservationsDto.add( preReservationMapper.preReservationToPreReservationDto(preReservation) );
            }
        }
        log.info("List<PreReservationDto> : " + preReservationsDto);
        return preReservationsDto;
    }

    //------------------------- SETTER -------------------------
    public void setPreReservationDao(PreReservationDao preReservationDao) {
        this.preReservationDao = preReservationDao;
    }

    public void setPreReservationMapper(PreReservationMapper preReservationMapper) {
        this.preReservationMapper = preReservationMapper;
    }
}
