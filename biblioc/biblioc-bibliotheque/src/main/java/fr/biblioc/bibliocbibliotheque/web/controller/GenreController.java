package fr.biblioc.bibliocbibliotheque.web.controller;

import fr.biblioc.bibliocbibliotheque.dao.GenreDao;
import fr.biblioc.bibliocbibliotheque.model.Genre;
import fr.biblioc.bibliocbibliotheque.web.exceptions.ErrorAddException;
import fr.biblioc.bibliocbibliotheque.web.exceptions.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller de la classe {@link Genre}
 */
@RestController
public class GenreController implements HealthIndicator {
    //------------------------- ATTRIBUTS -------------------------

    @Autowired
    GenreDao genreDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    /**
     * Indique le status du microservice
     * @return etat du microservice
     */
    @Override
    public Health health() {
        List<Genre> genres = genreDao.findAll();

        if(genres.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

    /**
     * Affiche la liste de tous les genres
     * @return liste de {@link Genre}
     */
    @GetMapping(value = "/Genres")
    public List<Genre> listeDesGenres(){

        List<Genre> genres = genreDao.findAll();

        if(genres.isEmpty()){
            throw new ObjectNotFoundException("Aucun genre n'a été trouvé");
        }

        log.info("Récupération de la liste des genres");

        return genres;

    }

    /**
     * Récuperer un genre par son id
     * @param id int
     * @return bean {@link Genre}
     */
    @GetMapping( value = "/Genres/{id}")
    public Optional<Genre> recupererUnGenre(@PathVariable int id) {

        Optional<Genre> genre = genreDao.findById(id);

        if(!genre.isPresent())  throw new ObjectNotFoundException("Le genre correspondant à l'id " + id + " n'existe pas");

        return genre;
    }

    /**
     * Ajouter un genre
     * @param genre bean {@link Genre}
     * @return ResponseEntity bean {@link Genre} renvoi un http status.
     */
    @PostMapping(value = "/Genres")
    public ResponseEntity<Genre> addGenre(Genre genre){

        Genre newGenre = genreDao.save(genre);

        if(newGenre == null) throw new ErrorAddException("Impossible d'ajouter ce genre");

        return new ResponseEntity<Genre>(genre, HttpStatus.CREATED);
    }

    /**
     * Permet de mettre à jour un genre existant.
     * @param genre bean {@link Genre}
     */
    @PutMapping(value = "/Genres")
    public void updateGenre(@RequestBody Genre genre) {

        genreDao.save(genre);
    }
}
