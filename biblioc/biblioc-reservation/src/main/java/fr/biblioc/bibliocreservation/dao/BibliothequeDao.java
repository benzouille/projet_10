package fr.biblioc.bibliocreservation.dao;

import fr.biblioc.bibliocreservation.model.Bibliotheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliothequeDao extends JpaRepository<Bibliotheque, Integer> {
}
