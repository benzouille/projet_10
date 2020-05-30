package fr.biblioc.bibliocreservation.dto;

import fr.biblioc.bibliocreservation.model.Exemplaire;

import java.sql.Date;

/**
 * Dto de l'objet Reservation
 */
public class ReservationDto {

    //------------------------- ATTRIBUTS -------------------------

    private int id_reservation;

    private int id_compte;

    private Date date_emprunt;

    private Boolean extension;

    private Boolean rendu;

    private Exemplaire exemplaire;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public ReservationDto() {
    }

    /**
     * constructeur avec parametres
     * @param id_compte int
     * @param date_emprunt Date
     * @param extension boolean
     * @param rendu boolean
     * @param exemplaire Objet Exemplaire
     */
    public ReservationDto(int id_compte, Date date_emprunt, Boolean extension, Boolean rendu, Exemplaire exemplaire) {
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
        return "ReservationDto{" +
                "id_reservation=" + id_reservation +
                ", id_compte=" + id_compte +
                ", date_emprunt=" + date_emprunt +
                ", extension=" + extension +
                ", rendu=" + rendu +
                ", exemplaire=" + exemplaire +
                '}';
    }
}