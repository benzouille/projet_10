package fr.biblioc.bibliocbibliotheque.dao;

import fr.biblioc.bibliocbibliotheque.model.Editeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface Dao pour JPA Hibernate
 */
@Repository
public interface EditeurDao extends JpaRepository<Editeur, Integer> {
}
