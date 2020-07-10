package fr.biblioc.bibliocclientUi.beans.reservation;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean Bibliotheque coté client
 */
public class BibliothequeBean {

    //------------------------- ATTRIBUTS -------------------------

    private int id_biblio;

    @NotNull
    private String nom;

    @NotNull
    private AdresseBean adresse;

    private List<ListeAttenteBean> listesAttentes;

    private List<ExemplaireBean> exemplaires;
    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public BibliothequeBean() {
        listesAttentes = new ArrayList<>();
        exemplaires = new ArrayList<>();
    }

    /**
     * Constructeur avec paramètres
     * @param nom String nom
     * @param adresse Objet Adresse
     */
    public BibliothequeBean(@NotNull String nom, @NotNull AdresseBean adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    //------------------------- METHODE -------------------------

    /**
     * methode permettant d'obtenir le nombre d'exemplaire d'un livre dans la bibliotheque.
     * @return int du nombre d'exemplaire
     */
    public int countExemplaireLivre(){
        int nbLivre =0;

        if(!exemplaires.isEmpty()){
                    nbLivre = exemplaires.size();
        }
        return nbLivre;
    }

    public void addExemplaire(ExemplaireBean exemplaire){
        exemplaires.add(exemplaire);
    }

    public void addListAttente(ListeAttenteBean listeAttente) {
        listesAttentes.add(listeAttente);
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getid_biblio() {
        return id_biblio;
    }

    public void setid_biblio(int id_biblio) {
        this.id_biblio = id_biblio;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public AdresseBean getAdresse() {
        return adresse;
    }

    public void setAdresse(AdresseBean adresse) {
        this.adresse = adresse;
    }

    public List<ListeAttenteBean> getListesAttentes() {
        return listesAttentes;
    }

    public void setListesAttentes(List<ListeAttenteBean> listesAttentes) {
        this.listesAttentes = listesAttentes;
    }

    public List<ExemplaireBean> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(List<ExemplaireBean> exemplaires) {
        this.exemplaires = exemplaires;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "BibliothequeBean{" +
                "nom='" + nom + '\'' +
                ", adresse=" + adresse +
                ", exemplaires=" + exemplaires +
                '}';
    }
}
