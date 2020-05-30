package fr.biblioc.bibliocauthentification.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Bean Role representant la table role de la bdd
 */
@Entity
public class Role {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue
    private int id_role;

    @NotNull
    private String role;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public Role() {
    }

    /**
     * constructeur avec parametre
     * @param role String
     */
    public Role(@NotNull String role) {
        this.role = role;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Role{" +
                "id_role=" + id_role +
                ", role='" + role + '\'' +
                '}';
    }
}
