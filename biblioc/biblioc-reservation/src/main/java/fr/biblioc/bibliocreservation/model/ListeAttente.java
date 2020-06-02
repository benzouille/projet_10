package fr.biblioc.bibliocreservation.model;

import fr.biblioc.bibliocreservation.web.exceptions.ErrorAddException;

import java.util.ArrayList;
import java.util.List;

public class ListeAttente {

    //------------------------- ATTRIBUTS -------------------------

    private int nbreExemplaire;

    private List<PreReservation> preReservationList;

    private String titre;

    private int id_bibliotheque;

    public ListeAttente(){
        preReservationList = new ArrayList<PreReservation>();
    }

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

    public void delToList(PreReservation preReservation){
        preReservationList.remove(preReservation);
    }
}
