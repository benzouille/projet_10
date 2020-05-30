package fr.biblioc.bibliocbibliotheque.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Bean genre correspondant à la table genre de la bdd
 */
@Entity
@Table(name = "genre")
public class Genre {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue
    @Column(name = "id_genre")
    private int id_genre;

    @Column(name = "genre")
    private String genre;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public Genre() {
    }

    /**
     * constructeur avec parametres
     * @param id_genre int
     * @param genre string
     */
    public Genre(int id_genre, @NotNull String genre) {
        this.id_genre = id_genre;
        this.genre = genre;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getid_genre() {
        return id_genre;
    }

    public void setid_genre(int id_genre) {
        this.id_genre = id_genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Genre{" +
                "id_genre=" + id_genre +
                ", genre='" + genre + '\'' +
                '}';
    }
}
