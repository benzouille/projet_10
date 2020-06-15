package fr.biblioc.bibliocreservation.dao;

import fr.biblioc.bibliocreservation.model.ListeAttente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListeAttenteDao extends JpaRepository<ListeAttente, Integer> {
}
