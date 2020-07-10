package fr.biblioc.bibliocreservation.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "prereservation")
public class PreReservation {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue
    private int id_compte;

    @Column(name = "date")
    private Date datePreReservation;

    @JoinColumn(name = "id_liste_attente")
    @ManyToOne(fetch = FetchType.LAZY)
    private ListeAttente listeAttente;

    private boolean expire;

    //------------------------- CONSTRUCTEUR -------------------------

    public PreReservation() {
        expire = false;
    }

    public PreReservation(int id_compte, Date datePreReservation, ListeAttente listeAttente, boolean expire) {
        this.id_compte = id_compte;
        this.datePreReservation = datePreReservation;
        this.listeAttente = listeAttente;
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

    public ListeAttente getListeAttente() {
        return listeAttente;
    }

    public void setListeAttente(ListeAttente listeAttente) {
        this.listeAttente = listeAttente;
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
                ", listeAttente=" + listeAttente +
                ", expire=" + expire +
                '}';
    }
}
