package fr.biblioc.bibliocauthentification.dto;

/**
 * dto Role
 */
public class RoleDto {

    //------------------------- ATTRIBUTS -------------------------

    private int id_role;

    private String role;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public RoleDto() {
    }

    /**
     * constructeur avec parametre
     * @param role String
     */
    public RoleDto(String role) {
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