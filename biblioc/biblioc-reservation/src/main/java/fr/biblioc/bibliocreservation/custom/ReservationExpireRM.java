package fr.biblioc.bibliocreservation.custom;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper de la classe ReservationExpire
 */
public class ReservationExpireRM implements RowMapper<ReservationExpire> {

    @Override
    public ReservationExpire mapRow(ResultSet rs, int rownum) throws SQLException {

        ReservationExpire reservationExpire = new ReservationExpire();
        reservationExpire.setId_reservation(rs.getInt("id_reservation"));
        reservationExpire.setId_exemplaire(rs.getInt("id_exemplaire"));
        reservationExpire.setEmail(rs.getString("email"));
        reservationExpire.setPrenom(rs.getString("prenom"));
        reservationExpire.setBibliotheque(rs.getString("nom"));
        reservationExpire.setTitre(rs.getString("titre"));

        return reservationExpire;
    }
}
