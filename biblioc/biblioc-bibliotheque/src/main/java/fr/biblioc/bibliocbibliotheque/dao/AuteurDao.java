package fr.biblioc.bibliocbibliotheque.dao;

import fr.biblioc.bibliocbibliotheque.model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface Dao pour JPA Hibernate
 */
@Repository
public interface AuteurDao extends JpaRepository<Auteur, Integer> {

}
