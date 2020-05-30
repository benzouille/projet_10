package fr.biblioc.bibliocreservation.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Bean Reservation representant la table reservation de la bdd
 */
@Entity
public class Reservation {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue
    private int id_reservation;

    @NotNull
    private int id_compte;

    @NotNull
    private Date date_emprunt;

    @NotNull
    private Boolean extension;

    @NotNull
    private Boolean rendu;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_exemplaire")
    private Exemplaire exemplaire;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public Reservation() {
    }

    /**
     * constructeur avec parametres
     * @param id_compte int
     * @param date_emprunt Date
     * @param extension boolean
     * @param rendu boolean
     * @param exemplaire Objet Exemplaire
     */
    public Reservation(@NotNull int id_compte, @NotNull Date date_emprunt, @NotNull Boolean extension, @NotNull Boolean rendu, @NotNull Exemplaire exemplaire) {
        this.id_compte = id_compte;
        this.date_emprunt = date_emprunt;
        this.extension = extension;
        this.exemplaire = exemplaire;
        this.rendu = rendu;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_utilisateur() {
        return id_compte;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_compte = id_utilisateur;
    }

    public Date getDate_emprunt() {
        return date_emprunt;
    }

    public void setDate_emprunt(Date date_emprunt) {
        this.date_emprunt = date_emprunt;
    }

    public Boolean getExtension() {
        return extension;
    }

    public void setExtension(Boolean extension) {
        this.extension = extension;
    }

    public Boolean getRendu() {
        return rendu;
    }

    public void setRendu(Boolean rendu) {
        this.rendu = rendu;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Reservation{" +
                "id_reservation=" + id_reservation +
                ", id_compte=" + id_compte +
                ", date_emprunt=" + date_emprunt +
                ", extension=" + extension +
                ", rendu=" + rendu +
                ", exemplaire=" + exemplaire +
                '}';
    }
}
