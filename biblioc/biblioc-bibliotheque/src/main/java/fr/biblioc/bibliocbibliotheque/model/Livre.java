package fr.biblioc.bibliocbibliotheque.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Bean de livre correspondant à la table livre de la bdd
 */
@Entity(name = "livre")
public class Livre {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue
    private int id_livre;

    @NotNull
    private String isbn13;

    @ManyToOne
    @JoinColumn(name = "id_genre", referencedColumnName = "id_genre")
    private Genre genre;

    @NotNull
    private String titre;

    @ManyToMany(mappedBy = "bibliographie", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Auteur> auteurs;

    @NotNull
    private String resume;

    private String image;

    @NotNull
    @Max(4)
    private int annee_parution;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_editeur")
    private Editeur editeur;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public Livre() {
    }

    /**
     * Constructeur avec ses parametres
     * @param isbn13-
     * @param genre-
     * @param titre-
     * @param auteurs List d'auteurs
     * @param resume-
     * @param image-
     * @param annee_parution-
     * @param editeur-
     */
    public Livre(@NotNull String isbn13, Genre genre, @NotNull String titre, List<Auteur> auteurs, @NotNull String resume, String image, @NotNull @Max(4) int annee_parution, @NotNull Editeur editeur) {
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