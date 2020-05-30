package fr.biblioc.bibliocclientUi.beans.authentification;

import fr.biblioc.bibliocclientUi.beans.utilisateur.UtilisateurBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Bean Compte coté client
 */
public class CompteBean {
    //------------------------- ATTRIBUTS -------------------------

    private int id_compte;

    //regex email RFC 5322
    //"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])"

    @NotNull
    @Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$",
            message = "doit être une adresse email valide")
    private String email;

    @NotNull
    // ((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})
    private String password;

    private int id_role;

    private int id_utilisateur;

    private UtilisateurBean utilisateur;

    //------------------------- CONSTRUCTEURS -------------------------

    /**
     * Constructeur
     */
    public CompteBean() {
    }

    /**
     * Constructeur avec paramètres
     * @param email String not null
     * @param password String not null
     * @param id_role int peux être null
     * @param id_utilisateur int peux être null
     */
    public CompteBean(@NotNull String email, @NotNull String password, int id_role, int id_utilisateur) {
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

    public UtilisateurBean getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurBean utilisateur) {
        this.utilisateur = utilisateur;
    }

    //------------------------- TO STRING -------------------------

    @Override
    public String toString() {
        return "CompteBean{" +
                "id_compte=" + id_compte +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id_role=" + id_role +
                ", id_utilisateur=" + id_utilisateur +
                '}';
    }
}
