package fr.biblioc.bibliocreservation.dao;

import fr.biblioc.bibliocreservation.model.PreReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreReservationDao extends JpaRepository<PreReservation, Integer> {
}
