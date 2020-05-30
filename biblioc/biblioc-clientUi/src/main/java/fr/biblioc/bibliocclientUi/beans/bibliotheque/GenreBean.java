package fr.biblioc.bibliocclientUi.beans.bibliotheque;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Bean genre coté client
 */
public class GenreBean {
    //------------------------- ATTRIBUTS -------------------------

    private int id_genre;

    @NotNull
    private String genre;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public GenreBean() {
    }

    /**
     * constructeur avec parametres
     * @param id_genre int
     * @param genre string
     */
    public GenreBean(int id_genre, @NotNull String genre) {
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
