package fr.biblioc.utilisateur.dao;

import fr.biblioc.utilisateur.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface Dao pour JPA Hibernate
 */
@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {

    @Query(value = " SELECT id_utilisateur FROM utilisateur ORDER BY id_utilisateur DESC LIMIT 1;", nativeQuery = true)
    int findLastId_compte();
}
