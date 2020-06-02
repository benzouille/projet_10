package fr.biblioc.bibliocreservation.web.controller;

import fr.biblioc.bibliocreservation.custom.ReservationExpire;
import fr.biblioc.bibliocreservation.custom.ReservationExpireDaoImpl;
import fr.biblioc.bibliocreservation.dao.ReservationDao;
import fr.biblioc.bibliocreservation.dto.ReservationDto;
import fr.biblioc.bibliocreservation.dto.ReservationExpireDto;
import fr.biblioc.bibliocreservation.mapper.ReservationExpireMapper;
import fr.biblioc.bibliocreservation.mapper.ReservationMapper;
import fr.biblioc.bibliocreservation.model.Reservation;
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

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller de la classe {@link Reservation}
 */
@RestController
public class ReservationController implements HealthIndicator {

    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private ReservationExpireDaoImpl reservationExpireDao;

    @Autowired
    private ReservationExpireMapper reservationExpireMapper;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     *
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Reservation> reservations = reservationDao.findAll();

        if (reservations.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de toutes les reservations
     *
     * @return liste de {@link Reservation}
     */
    @GetMapping(value = "/Reservations")
    public List<Reservation> listeDesReservations() {

        List<Reservation> reservations = reservationDao.findAll();

        if (reservations.isEmpty()) {
            throw new ObjectNotFoundException("Aucune reservation n'a été trouvée");
        }

        log.info("Récupération de la liste des reservations");

        return reservations;

    }

    /**
     * Affiche la liste de toutes les reservations en cours
     *
     * @return liste de {@link Reservation}
     */
    @GetMapping(value = "/Reservations/en_cours")
    public List<ReservationDto> listeDesReservationsEnCours() {
        log.info("Récupération de la liste des reservationsRenduFalse");
        List<Reservation> reservations = reservationDao.findAllByRenduFalse();
        List<ReservationDto> reservationsDto = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationsDto.add(reservationMapper.reservationToReservationDto(reservation));
        }

        return reservationsDto;
    }

    /**
     * Affiche la liste de toutes les reservations expirés
     *
     * @return liste de {@link ReservationExpireDto}
     */
    @GetMapping(value = "/Reservations/expire")
    public List<ReservationExpireDto> listeDesReservationsExpire() {
        log.info("Récupération de la liste des reservationsExpire");
        List<ReservationExpire> reservationsExpire = reservationExpireDao.readAll();
        List<ReservationExpireDto> reservationsExpireDto = new ArrayList<>();

        for (ReservationExpire reservationExpire : reservationsExpire) {
            reservationsExpireDto.add(reservationExpireMapper.reservationExpireToReservationExpireDto(reservationExpire));
        }

        return reservationsExpireDto;
    }

    /**
     * Récuperer une reservation par son id
     *
     * @param id int
     * @return bean {@link Reservation}
     */
    @GetMapping(value = "/Reservations/{id}")
    public Optional<Reservation> recupererUneReservation(@PathVariable int id) {

        Optional<Reservation> reservation = reservationDao.findById(id);

        if (!reservation.isPresent())
            throw new ObjectNotFoundException("La reservation correspondant à l'id " + id + " n'existe pas");

        return reservation;
    }

    /**
     * Récuperer des reservations par l'id_compte
     *
     * @param id_compte int
     * @return List {@link Reservation}
     */
    @GetMapping(value = "/Reservations/compte/{id_compte}")
    List<Reservation> getReservationById_compte(@PathVariable int id_compte) {

        List<Reservation> reservations = reservationDao.findAllById_compte(id_compte);
        List<ReservationDto> reservationsDto = new ArrayList<>();

        return reservations;
    }

    /**
     * Ajouter une reservation
     *
     * @param reservation bean {@link Reservation}
     * @return ResponseEntity Reservation renvoi un http status.
     */
    @PostMapping(value = "/Reservations")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {

        log.info("addReservation : " + reservation);

        Reservation newReservation = reservationDao.save(reservation);

        if (newReservation == null) throw new ErrorAddException("Impossible d'ajouter cette reservation");

        return new ResponseEntity<Reservation>(reservation, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour une reservation existante.
     * @param reservation bean {@link Reservation}
     **/
    @PutMapping(value = "/Reservations")
    public void updateReservation(@RequestBody Reservation reservation) {

        reservationDao.save(reservation);
    }

    //------------------------- METHODE INTERNE -------------------------

    /**
     * Renvoie la date du jour
     *
     * @return java.sql.Date
     */
    private Date newDate() {
        LocalDate localDate = LocalDate.now();

        return Date.valueOf(localDate);
    }

    /**
     * Permet de calculer la date retour maximal en fonction de l'extention
     *
     * @param dateDebut Date
     * @param extention Boolean
     * @return java.sql.Date
     */
    private Date dateDebutToFin(Date dateDebut, boolean extention) {
        long dureePret = 28;
        LocalDate dateFin;

        if (!extention) {
            dateFin = dateDebut.toLocalDate().plusDays(dureePret);
        } else {
            dateFin = dateDebut.toLocalDate().plusDays(dureePret * 2);
        }

        return Date.valueOf(dateFin);
    }
}
