package fr.biblioc.bibliocreservation.dao;

import fr.biblioc.bibliocreservation.model.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExemplaireDao extends JpaRepository<Exemplaire, Integer> {

    @Query(value = "SELECT EXISTS(SELECT true from exemplaire where id_exemplaire=:id_exemplaire)", nativeQuery = true)
    boolean existsById_exemplaire(@Param("id_exemplaire") int id_exemplaire);

    @Query(value = "SELECT * FROM exemplaire WHERE id_livre = :id_livre", nativeQuery = true)
    List<Exemplaire> findAllById_livre(@Param("id_livre") int id_livre);

    @Query(value = "SELECT * FROM exemplaire inner join livre on livre.id_livre = exemplaire.id_livre inner join auteur_livre on auteur_livre.livre_id = livre.id_livre WHERE auteur_livre.auteur_id =:auteur_id AND livre.id_genre =:id_genre AND exemplaire.id_biblio =:id_biblio", nativeQuery = true)
    List<Exemplaire> multiCrit(@Param("auteur_id") int auteur_id, @Param("id_genre") int id_genre, @Param("id_biblio") int id_biblio);
}
