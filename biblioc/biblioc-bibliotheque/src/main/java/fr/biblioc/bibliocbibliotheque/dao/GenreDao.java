package fr.biblioc.bibliocbibliotheque.dao;

import fr.biblioc.bibliocbibliotheque.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface Dao pour JPA Hibernate
 */
@Repository
public interface GenreDao extends JpaRepository<Genre, Integer> {
}
