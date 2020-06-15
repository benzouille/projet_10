package fr.biblioc.bibliocreservation.dto;

import java.util.Date;

public class PreReservationDto {
    //------------------------- ATTRIBUTS -------------------------

    private int id_compte;

    private Date datePreReservation;

    private int id_liste_attente;

    private boolean expire;

    //------------------------- CONSTRUCTEUR -------------------------

    public PreReservationDto() {
        expire = false;
    }

    public PreReservationDto(int id_compte, Date datePreReservation, int id_liste_attente, boolean expire) {
        this.id_compte = id_compte;
        this.datePreReservation = datePreReservation;
        this.id_liste_attente = id_liste_attente;
        this.expire = expire;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getId_compte() {
        return id_compte;
    }

    public void setId_compte(int id_compte) {
        this.id_compte = id_compte;
    }

    public Date getDatePreReservation() {
        return datePreReservation;
    }

    public void setDatePreReservation(Date datePreReservation) {
        this.datePreReservation = datePreReservation;
    }

    public int getId_liste_attente() {
        return id_liste_attente;
    }

    public void setId_liste_attente(int id_liste_attente) {
        this.id_liste_attente = id_liste_attente;
    }

    public boolean isExpire() {
        return expire;
    }

    public void setExpire(boolean expire) {
        this.expire = expire;
    }

    //------------------------- TO_STRING -------------------------


    @Override
    public String toString() {
        return "PreReservation{" +
                "datePreReservation=" + datePreReservation +
                ", id_liste_attente=" + id_liste_attente +
                ", expire=" + expire +
                '}';
    }
}
