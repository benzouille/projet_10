package fr.biblioc.bibliocclientUi.beans.bibliotheque;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Bean livre coté client
 */
public class LivreBean {

    //------------------------- ATTRIBUTS -------------------------

    private int id_livre;

    @NotNull
    private String isbn13;

    @NotNull
    private GenreBean genre;

    @NotNull
    @Size(min = 1, max = 20)
    private String titre;

    @NotNull
    private List<AuteurBean> auteurs;

    @NotNull
    private String resume;

    private String image;

    @NotNull
    @Max(4)
    private int annee_parution;

    @NotNull
    private EditeurBean editeur;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public LivreBean() {
    }

    /**
     * Constructeur avec ses parametres
     * @param isbn13-
     * @param genre-
     * @param titre-
     * @param auteurs list d'auteur
     * @param resume-
     * @param image-
     * @param annee_parution-
     * @param editeur-
     */
    public LivreBean(@NotNull String isbn13, @NotNull GenreBean genre, @NotNull @Size(min= 1, max = 20) String titre, List<AuteurBean> auteurs, @NotNull String resume, String image, @NotNull @Max(4) int annee_parution, @NotNull EditeurBean editeur) {
        this.isbn13 = isbn13;
        this.genre = genre;
        this.titre = titre;
        this.auteurs =auteurs;
        this.resume = resume;
        this.image = image;
        this.annee_parution = annee_parution;
        this.editeur = editeur;
    }
//------------------------- GETTER/SETTER -------------------------

    public int getid_livre() {
        return id_livre;
    }

    public void setid_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public GenreBean getGenre() {
        return genre;
    }

    public void setGenre(GenreBean genre) {
        this.genre = genre;
    }

    public String getTitre() {
        return titre;
    }

    public List<AuteurBean> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<AuteurBean> auteurs) {
        this.auteurs = auteurs;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAnnee_parution() {
        return annee_parution;
    }

    public void setAnnee_parution(int annee_parution) {
        this.annee_parution = annee_parution;
    }

    public EditeurBean getEditeur() {
        return editeur;
    }

    public void setEditeur(EditeurBean editeur) {
        this.editeur = editeur;
    }

//------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "LivreBean{" +
                "id_livre=" + id_livre +
                ", isbn13='" + isbn13 + '\'' +
                ", genre=" + genre +
                ", titre='" + titre + '\'' +
                ", auteurs=" + auteurs +
                ", resume='" + resume + '\'' +
                ", image='" + image + '\'' +
                ", annee_parution=" + annee_parution +
                ", editeur=" + editeur +
                '}';
    }
}
