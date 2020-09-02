package fr.biblioc.bibliocclientUi.proxies;

import fr.biblioc.bibliocclientUi.beans.reservation.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Proxy du microservice reservation.
 */
@FeignClient(name = "biblioc-reservation", url = "localhost:9003")
public interface BibliocReservationProxy {

    @GetMapping(value = "/Reservations")
    List<ReservationBean> listReservations();

    @GetMapping(value = "/ListeAttente/{id_livre}")
    List<ListeAttenteBean> listAttente(@PathVariable("id_livre") int id_livre);

    @GetMapping(value = "/listAttenteByid/{id_liste_attente}")
    ListeAttenteBean listAttenteById(@PathVariable("id_liste_attente") int id_liste_attente);

    @GetMapping(value = "/ListeAttente/{id_livre}/{id_bibliotheque}")
    ListeAttenteBean listAttente(@PathVariable("id_livre") int id_livre,@PathVariable("id_bibliotheque") int id_bibliotheque);

    @GetMapping(value = "/PreReservations/{id_compte}")
    List<PreReservationBean> listPreReservationByIdUser(@PathVariable("id_compte") int id_compte);

    @GetMapping( value = "/PreReservation/{id_prereservation}")
    PreReservationBean preReservationById(@PathVariable int id_prereservation);

    @GetMapping(value = "/PreReservation/notExpired")
    List<PreReservationBean> listPreReservationNotExpired();

    @PostMapping(value = "/PreReservation")
    PreReservationBean newPrereservation(@RequestBody PreReservationBean prereservation);

    @PutMapping(value = "/PreReservation")
    PreReservationBean updatePreReservation(@RequestBody PreReservationBean preReservation);

    @GetMapping(value = "/Reservations/en_cours")
    List<ReservationBean> listeReservationsEnCours();

    @GetMapping(value = "/Reservations/{id}")
    ReservationBean getReservation(@PathVariable("id") int id);

    @GetMapping(value = "/Reservations/compte/{id_compte}")
    List<ReservationBean> getReservationById_compte(@PathVariable("id_compte") int id_compte);

    @PostMapping(value = "/Reservations")
    ReservationBean newReservation(@RequestBody ReservationBean reservation);

    @PutMapping(value = "/Reservations")
    ReservationBean updateReservation(@RequestBody ReservationBean reservation);

    @GetMapping(value = "/Bibliotheques")
    List <BibliothequeBean> listBibliotheques();

    @GetMapping(value = "/Bibliotheques/{id}")
    List <BibliothequeBean> getBibliotheque(@PathVariable("id") int id);

    @GetMapping(value = "/Exemplaires")
    List<ExemplaireBean> listExemplaires();

    @GetMapping(value = "/Exemplaires/existe/{id}")
    boolean isExemplaire(@PathVariable("id") int id);

    @GetMapping( value = "/Exemplaires/count/{id_livre}/{id_biblio}")
    int exemplaireCount(@PathVariable("id_livre") int id_livre,@PathVariable("id_biblio") int id_biblio);

    @GetMapping(value = "/Exemplaires/{id}")
    ExemplaireBean getExemplaire(@PathVariable("id") int id);

    @PutMapping(value = "/Exemplaires")
    ExemplaireBean updateExemplaire(@RequestBody ExemplaireBean exemplaire);

    @GetMapping(value = "/Exemplaires-livre/{id}")
    List<ExemplaireBean> getExemplairesByIdLivre(@PathVariable("id") int id);

    @GetMapping(value = "/Exemplaires-livre-dispo/{id}")
    List<ExemplaireBean> getExemplairesByIdLivreDispo(@PathVariable("id") int id);

    @GetMapping(value = "/Exemplaires-livre")
    List<ExemplaireBean>rechercheMulti(@RequestParam("multicrit") String multicrit);
}
