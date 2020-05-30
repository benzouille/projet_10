package fr.biblioc.bibliocbibliotheque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Bean editeur correspondant à la table editeur de la bdd
 */
@Entity
public class Editeur {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue
    private int id_editeur;

    @NotNull
    private String nom;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public Editeur() {
    }

    /**
     * constructeur avec parametres
     * @param nom string
     */
    public Editeur(@NotNull @Max(100) String nom) {
        this.nom = nom;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getid_editeur() {
        return id_editeur;
    }

    public void setid_editeur(int id_editeur) {
        this.id_editeur = id_editeur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Editeur{" +
                "id_editeur=" + id_editeur +
                ", nom='" + nom + '\'' +
                '}';
    }
}
