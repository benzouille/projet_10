package fr.biblioc.bibliocreservation.custom;

/**
 * Interface permetant de faire le lien entre le module business et consumer .
 */
public interface DaoFactory {

    ReservationExpireDao getReservationExpireDao();
    void setReservationExpireDao(ReservationExpireDao reservationExpireDao);
}
