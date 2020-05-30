package fr.biblioc.bibliocbibliotheque.dto;

/**
 * Dto editeur
 */
public class EditeurDto {

    //------------------------- ATTRIBUTS -------------------------

    private int id_editeur;

    private String nom;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public EditeurDto() {
    }

    /**
     * constructeur avec parametres
     * @param nom string
     */
    public EditeurDto(String nom) {
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
