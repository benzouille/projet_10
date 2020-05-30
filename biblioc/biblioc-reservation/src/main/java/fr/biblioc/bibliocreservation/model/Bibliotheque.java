package fr.biblioc.bibliocreservation.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Bean Bibliothèque representant la table bibliotheque de la bdd
 */
@Entity(name = "bibliotheque")
public class Bibliotheque {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue
    private int id_biblio;

    @NotNull
    private String nom;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_adresse")
    private Adresse adresse;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public Bibliotheque() {
    }

    /**
     * Constructeur avec paramètres
     * @param nom String nom
     * @param adresse Adresse adresse
     */
    public Bibliotheque(@NotNull String nom, @NotNull Adresse adresse) {
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
