package fr.biblioc.bibliocreservation.dto;

import fr.biblioc.bibliocreservation.model.PreReservation;
import fr.biblioc.bibliocreservation.web.exceptions.ErrorAddException;
import fr.biblioc.bibliocreservation.web.exceptions.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ListeAttenteDto {
    //------------------------- ATTRIBUTS -------------------------

    private int id_liste_attente;

    private int nbreExemplaire;

    private List<PreReservation> preReservationList;

    private String id_livre;

    private int id_bibliotheque;

    //------------------------- CONSTRUCTEUR -------------------------

    public ListeAttenteDto(){
        preReservationList = new ArrayList<PreReservation>();
    }

    //------------------------- METHODES -------------------------

    /**
     * La liste de réservation ne peut comporter qu’un maximum de personnes correspondant à 2x le nombre d’exemplaires de l’ouvrage.
     * @param preReservation
     */
    public void addToList(PreReservation preReservation){
        if(preReservationList.size() >= nbreExemplaire*2){
            preReservationList.add(preReservation);
        }
        else {
            throw new ErrorAddException("la liste de réservation est pleine");
        }
    }

    public void delToList(PreReservation preReservation) {
        if(preReservationList.contains(preReservation)){
            preReservationList.remove(preReservation);

        }
        else{
            throw new ObjectNotFoundException("la préreservation n'existe pas dans la liste");
        }
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

    public String getId_livre() {
        return id_livre;
    }

    public void setId_livre(String id_livre) {
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
                ", preReservationList=" + preReservationList +
                ", id_livre='" + id_livre + '\'' +
                ", id_bibliotheque=" + id_bibliotheque +
                '}';
    }
}
