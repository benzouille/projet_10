package fr.biblioc.bibliocclientUi.beans.reservation;

import fr.biblioc.bibliocclientUi.beans.utilities.ErrorAddException;
import fr.biblioc.bibliocclientUi.beans.utilities.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ListeAttenteBean {
    //------------------------- ATTRIBUTS -------------------------

    private int id_liste_attente;

    private int nbreExemplaire;

    private List<PreReservationBean> preReservationList;

    private int id_livre;

    private int id_bibliotheque;

    //------------------------- CONSTRUCTEUR -------------------------

    public ListeAttenteBean(){
        preReservationList = new ArrayList<PreReservationBean>();
    }

    //------------------------- METHODES -------------------------

    /**
     * La liste de réservation ne peut comporter qu’un maximum de personnes correspondant à 2x le nombre d’exemplaires de l’ouvrage.
     * @param preReservation
     */
    public void addToList(PreReservationBean preReservation) throws ErrorAddException {
        if(preReservationList.size() >= nbreExemplaire*2){
            preReservationList.add(preReservation);
        }
        else {
            throw new ErrorAddException("la liste de réservation est pleine");
        }
    }

    public void delToList(PreReservationBean preReservation) throws ObjectNotFoundException {
        if(preReservationList.contains(preReservation)){
            preReservationList.remove(preReservation);

        }
        else{
            throw new ObjectNotFoundException("la préreservation n'existe pas dans la liste");
        }
    }

    public int getSizeListAttente(){
        int sizeListAttente = 0;
        if(nbreExemplaire != 0){
            int nbrePreReserv = getPreReservationList().size();
            sizeListAttente = nbreExemplaire * 2 - nbrePreReserv;
        }
        return sizeListAttente;
    }

    public int getnbrePrereservation(){
        return preReservationList.size();
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

    public List<PreReservationBean> getPreReservationList() {
        return preReservationList;
    }

    public void setPreReservationList(List<PreReservationBean> preReservationList) {
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
                ", preReservationList=" + preReservationList +
                ", id_livre='" + id_livre + '\'' +
                ", id_bibliotheque=" + id_bibliotheque +
                '}';
    }
}
