package fr.biblioc.bibliocbibliotheque.dto;

import fr.biblioc.bibliocbibliotheque.model.Auteur;
import fr.biblioc.bibliocbibliotheque.model.Editeur;
import fr.biblioc.bibliocbibliotheque.model.Genre;

import java.util.List;

/**
 * Dto Livre
 */
public class LivreDto {

    //------------------------- ATTRIBUTS -------------------------

    private int id_livre;

    private String isbn13;

    private Genre genre;

    private String titre;

    private List<Auteur> auteurs;

    private String resume;

    private String image;

    private int annee_parution;

    private Editeur editeur;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public LivreDto() {
    }

    /**
     * Constructeur avec ses parametres
     *
     * @param isbn13-
     * @param genre-
     * @param titre-
     * @param auteurs List d'auteurs
     * @param resume-
     * @param image-
     * @param annee_parution-
     * @param editeur-
     */
    public LivreDto(String isbn13, Genre genre, String titre, List<Auteur> auteurs, String resume, String image, int annee_parution,Editeur editeur) {
        this.isbn13 = isbn13;
        this.genre = genre;
        this.titre = titre;
        this.auteurs = auteurs;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<Auteur> auteurs) {
        this.auteurs = auteurs;
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

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

//------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Livre{" +
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
