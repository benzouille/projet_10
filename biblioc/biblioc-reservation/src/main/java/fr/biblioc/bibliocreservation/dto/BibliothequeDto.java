package fr.biblioc.bibliocreservation.dto;

import fr.biblioc.bibliocreservation.model.Adresse;

/**
 * Dto de l'objet Bibliothèque
 */
public class BibliothequeDto {

    //------------------------- ATTRIBUTS -------------------------

    private int id_biblio;

    private String nom;

    private Adresse adresse;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public BibliothequeDto() {
    }

    /**
     * Constructeur avec paramètres
     * @param nom String nom
     * @param adresse Adresse adresse
     */
    public BibliothequeDto(String nom, Adresse adresse) {
        this.nom = nom;
        this.adresse = adresse;
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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Bibliotheque{" +
                "id_biblio=" + id_biblio +
                ", nom='" + nom + '\'' +
                ", adresse=" + adresse +
                '}';
    }
}
