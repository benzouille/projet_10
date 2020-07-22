package fr.biblioc.mail.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



    /**
     * Controller de la classe {@link Reservation}
     */
    @RestController
    public class MailController implements HealthIndicator {

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
}
