package fr.biblioc.bibliocreservation.custom;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe implementant l'inteface ReservationExpireDao retournant les reservations dont la date de retour est dépassé.
 */
@Repository
public class ReservationExpireDaoImpl extends AbstractDao implements ReservationExpireDao {

    private ReservationExpireRM reservationExpireRM = new ReservationExpireRM();

    @Override
    public List<ReservationExpire> readAll() {

        String vSQL = "SELECT reservation.id_reservation, exemplaire.id_exemplaire, compte.email, utilisateur.prenom, bibliotheque.nom, livre.titre FROM reservation inner join compte on reservation.id_compte = compte.id_compte inner join utilisateur on compte.id_utilisateur = utilisateur.id_utilisateur inner join exemplaire on reservation.id_exemplaire = exemplaire.id_exemplaire inner join bibliotheque on exemplaire.id_biblio = bibliotheque.id_biblio inner join livre on exemplaire.id_livre = livre.id_livre WHERE reservationExpire(reservation.date_emprunt, reservation.extension) < current_date AND reservation.rendu = false";
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        List<ReservationExpire> vListReservationExpire = vJdbcTemplate.query(vSQL, reservationExpireRM);
        return vListReservationExpire;
    }
}
