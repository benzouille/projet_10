package fr.biblioc.bibliocauthentification.dto;

/**
 * dto Compte
 */
public class CompteDto {
    //------------------------- ATTRIBUTS -------------------------

    private int id_compte;

    private String email;

    private String password;

    private int id_role;

    private int id_utilisateur;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public CompteDto() {
    }

    /**
     * Constructeur avec parametres
     * @param email String
     * @param password String
     * @param id_role int
     * @param id_utilisateur int
     */
    public CompteDto(String email, String password, int id_role, int id_utilisateur) {
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

