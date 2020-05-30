package fr.biblioc.bibliocclientUi.beans.authentification;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Bean role coté client
 */
public class RoleBean {

    //------------------------- ATTRIBUTS -------------------------

    private int id_role;

    @NotNull
    private String role;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    protected RoleBean() {
    }

    /**
     * constructeur avec parametre
     * @param role String
     */
    public RoleBean(@NotNull @Max(100) String role) {
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
