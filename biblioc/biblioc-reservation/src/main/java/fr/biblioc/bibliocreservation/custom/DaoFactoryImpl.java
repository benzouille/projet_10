package fr.biblioc.bibliocreservation.custom;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Objet permettant l'injections des DAO
 */
@Named("daoFactory")
public class DaoFactoryImpl implements DaoFactory {

    @Inject
    ReservationExpireDao reservationExpireDaoImpl;

    @Override
    public ReservationExpireDao getReservationExpireDao() {
        return reservationExpireDaoImpl;
    }

    @Override
    public void setReservationExpireDao(ReservationExpireDao reservationExpireDao) {
        this.reservationExpireDaoImpl = reservationExpireDao;
    }
}
