package fr.biblioc.bibliocauthentification.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Bean Compte representant la table compte de la bdd
 */
@Entity
public class Compte {
    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue
    private int id_compte;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private int id_role;

    @NotNull
    private int id_utilisateur;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public Compte() {
    }

    /**
     * Constructeur avec parametres
     * @param email String
     * @param password String
     * @param id_role int
     * @param id_utilisateur int
     */
    public Compte(@NotNull String email, @NotNull String password, @NotNull int id_role, @NotNull int id_utilisateur) {
        this.email = email;
        this.password = password;
        this.id_role = id_role;
        this.id_utilisateur = id_utilisateur;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getId_compte() {
        return id_compte;
    }

    public void setId_compte(int id_compte) {
        this.id_compte = id_compte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Compte{" +
                "id_compte=" + id_compte +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id_role=" + id_role +
                ", id_utilisateur=" + id_utilisateur +
                '}';
    }
}
