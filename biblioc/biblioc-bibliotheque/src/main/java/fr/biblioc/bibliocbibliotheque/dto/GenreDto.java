package fr.biblioc.bibliocbibliotheque.dto;

import javax.validation.constraints.NotNull;

/**
 * Dto genre
 */
public class GenreDto {

    //------------------------- ATTRIBUTS -------------------------

    private int id_genre;

    @NotNull
    private String genre;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public GenreDto() {
    }

    /**
     * constructeur avec parametres
     * @param id_genre int
     * @param genre string
     */
    public GenreDto(int id_genre, @NotNull String genre) {
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