package fr.biblioc.bibliocreservation.dao;

import fr.biblioc.bibliocreservation.model.ListeAttente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListeAttenteDao extends JpaRepository<ListeAttente, Integer> {

    @Query(value = "SELECT * FROM liste_attente WHERE id_livre = :id_livre", nativeQuery = true)
    List<ListeAttente> findByIdLivre(@Param("id_livre") int id_livre);

    @Query(value = "SELECT * FROM liste_attente WHERE id_livre = :id_livre AND id_biblio =:id_biblio", nativeQuery = true)
    ListeAttente findByIdLivreAndIdBiblio(@Param("id_livre") int id_livre, @Param("id_biblio") int id_biblio);

}
