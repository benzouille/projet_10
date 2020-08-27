package fr.biblioc.bibliocreservation.model;

import fr.biblioc.bibliocreservation.web.exceptions.ErrorAddException;
import fr.biblioc.bibliocreservation.web.exceptions.ObjectNotFoundException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "liste_attente")
public class ListeAttente {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    private int id_liste_attente;

    @Transient
    private int nbreExemplaire;

    @OneToMany(mappedBy = "id_liste_attente")
    private List<PreReservation> preReservationList;

    private int id_livre;

    @Column(name = "id_biblio")
    private int id_bibliotheque;

    //------------------------- CONSTRUCTEUR -------------------------

    public ListeAttente(){
        preReservationList = new ArrayList<PreReservation>();
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getId_liste_attente() {
        return id_liste_attente;
    }

    public void setId_liste_attente(int id_liste_attente) {
        this.id_liste_attente = id_liste_attente;
    }

    public int getNbreExemplaire() {
        return nbreExemplaire;
    }

    public void setNbreExemplaire(int nbreExemplaire) {
        this.nbreExemplaire = nbreExemplaire;
    }

    public List<PreReservation> getPreReservationList() {
        return preReservationList;
    }

    public void setPreReservationList(List<PreReservation> preReservationList) {
        this.preReservationList = preReservationList;
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public int getId_bibliotheque() {
        return id_bibliotheque;
    }

    public void setId_bibliotheque(int id_bibliotheque) {
        this.id_bibliotheque = id_bibliotheque;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "ListeAttente{" +
                "id_liste_attente=" + id_liste_attente +
                ", nbreExemplaire=" + nbreExemplaire +
//                ", preReservationList=" + preReservationList +
                ", id_livre='" + id_livre + '\'' +
                ", id_bibliotheque=" + id_bibliotheque +
                '}';
    }
}
