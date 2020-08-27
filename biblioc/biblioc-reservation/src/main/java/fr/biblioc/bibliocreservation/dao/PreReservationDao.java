package fr.biblioc.bibliocreservation.dao;

import fr.biblioc.bibliocreservation.model.PreReservation;
import fr.biblioc.bibliocreservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PreReservationDao extends JpaRepository<PreReservation, Integer> {

    @Query(value = "SELECT * FROM prereservation WHERE expire = false AND mail_send = true", nativeQuery = true)
    List<PreReservation> findAllByNotExpired();

    @Query(value = "SELECT * FROM prereservation WHERE id_compte = :id_compte AND expire = false", nativeQuery = true)
    List<PreReservation> findAllById_compte(@Param("id_compte") int id_compte);

    @Query(value = "INSERT INTO prereservation (id_prereservation, id_compte, date, id_liste_attente, expire, mail_send) VALUES (:id_prereservation, :id_compte, :date, :id_liste_attente, :expire, :mail_send)", nativeQuery = true)
    void newPrereservation(@Param("id_prereservation") int id_prereservation,
                           @Param("id_compte") int id_compte,
                           @Param("date") Date date,
                           @Param("id_liste_attente") int id_liste_attente,
                           @Param("expire") boolean expire,
                           @Param("mail_send") boolean mail_send);

}
