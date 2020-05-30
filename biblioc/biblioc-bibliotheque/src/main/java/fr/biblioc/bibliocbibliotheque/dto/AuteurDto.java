package fr.biblioc.bibliocbibliotheque.dto;

import fr.biblioc.bibliocbibliotheque.model.Livre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Dto auteur
 */
public class AuteurDto {

    //------------------------- ATTRIBUTS -------------------------

    private int id;

    private String nom;

    private String prenom;

    private Date date_naissance;

    private Date date_deces;

    private List<Livre> bibliographie;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public AuteurDto() {
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
    public AuteurDto(int id, String nom, String prenom, Date date_naissance, Date date_deces, List<Livre> bibliographie) {
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

        return "AuteurDto{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", date_naissance=" + date_naissance +
                ", date_deces=" + date_deces +
                ", bibliographie=" + id_livres +
                '}';
    }
}
