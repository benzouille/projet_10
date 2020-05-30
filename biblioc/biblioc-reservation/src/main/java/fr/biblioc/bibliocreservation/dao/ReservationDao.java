package fr.biblioc.bibliocreservation.dao;

import fr.biblioc.bibliocreservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT * FROM reservation WHERE id_compte = :id_compte", nativeQuery = true)
    List<Reservation> findAllById_compte(@Param("id_compte") int id_compte);

    List<Reservation> findAllByRenduFalse();
}
