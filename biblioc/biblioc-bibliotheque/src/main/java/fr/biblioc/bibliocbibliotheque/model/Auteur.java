package fr.biblioc.bibliocbibliotheque.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Bean auteur correspondant à la table auteur de la bdd
 */
@Entity(name = "auteur")
public class Auteur {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auteur")
    private int id;

    @NotBlank
    @Column(name = "nom")
    private String nom;

    @NotBlank
    @Column(name = "prenom")
    private String prenom;

    @NotBlank
    @Column(name = "date_naissance")
    private Date date_naissance;

    @Column(name = "date_deces")
    private Date date_deces;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "auteur_livre",
            joinColumns = { @JoinColumn(name = "auteur_id") },
            inverseJoinColumns = { @JoinColumn(name = "livre_id") } )
    @JsonManagedReference
    private List<Livre> bibliographie;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public Auteur() {
    }

    /**
     * constructeur avec parametres
     * @param id int
     * @param nom string
     * @param prenom string
     * @param date_naissance Date
     * @param date_deces Date
     * @param bibliographie List de Livre
     */
    public Auteur(int id, @NotNull String nom, @NotNull String prenom, @NotNull Date date_naissance, Date date_deces, List<Livre> bibliographie) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.date_deces = date_deces;
        this.bibliographie = bibliographie;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Date getDate_deces() {
        return date_deces;
    }

    public void setDate_deces(Date date_deces) {
        this.date_deces = date_deces;
    }

    public List<Livre> getBibliographie() {
        return bibliographie;
    }

    public void setBibliographie(List<Livre> bibliographie) {
        this.bibliographie = bibliographie;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {

        List<Integer>id_livres = new ArrayList<>();
        for(Livre livre : bibliographie){
            id_livres.add(livre.getid_livre());
        }

        return "Auteur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", date_naissance=" + date_naissance +
                ", date_deces=" + date_deces +
                ", bibliographie=" + id_livres +
                "}";
    }
}
