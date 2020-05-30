package fr.biblioc.bibliocbibliotheque.dao;

import fr.biblioc.bibliocbibliotheque.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface Dao pour JPA Hibernate
 */
@Repository
public interface LivreDao extends JpaRepository<Livre, Integer> {

    List<Livre> findByTitre(String titre);

    @Query(value = "SELECT * FROM livre WHERE id_genre = :id_genre", nativeQuery = true)
    List<Livre> findById_genre(@Param("id_genre") int genre);

}
