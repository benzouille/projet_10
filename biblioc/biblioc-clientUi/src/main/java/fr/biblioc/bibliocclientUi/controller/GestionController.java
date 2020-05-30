package fr.biblioc.bibliocclientUi.controller;

import fr.biblioc.bibliocclientUi.beans.authentification.CompteBean;
import fr.biblioc.bibliocclientUi.beans.reservation.ExemplaireBean;
import fr.biblioc.bibliocclientUi.beans.reservation.ReservationBean;
import fr.biblioc.bibliocclientUi.beans.utilisateur.UtilisateurBean;
import fr.biblioc.bibliocclientUi.proxies.BibliocAuthentificationProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocBibliothequeProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocReservationProxy;
import fr.biblioc.bibliocclientUi.proxies.BibliocUtilisateurProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Controller utilisant le proxy vers le microservice reservation
 */
@Controller
public class GestionController {

    //------------------------- PARAMETRE -------------------------

    @Autowired
    private BibliocUtilisateurProxy utilisateurProxy;

    @Autowired
    private BibliocBibliothequeProxy bibliothequeProxy;

    @Autowired
    private BibliocReservationProxy reservationProxy;

    @Autowired
    private BibliocAuthentificationProxy authentificationProxy;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //------------------------- METHODE -------------------------

    @RequestMapping(value= "/mes_emprunts", method = RequestMethod.GET)
    public ModelAndView empruntsUtilisateur(HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView("mes_emprunts");

        CompteBean compte = (CompteBean)request.getSession().getAttribute("compte");
        modelAndView.addObject("compte", compte);

        UtilisateurBean utilisateur = utilisateurProxy.getUtilisateur(compte.getId_utilisateur());
        List<ReservationBean> reservations = reservationProxy.getReservationById_compte(compte.getId_compte());

        //ajout des objets livres dans les reservations
        for (ReservationBean reservation : reservations){
            reservation.setUtilisateur(utilisateurProxy.getUtilisateur(reservation.getId_utilisateur()));
            reservation.setDate_retour(dateDebutToFin(reservation.getDate_emprunt(), reservation.getExtension()));
            reservation.getExemplaire().setLivre(bibliothequeProxy.getLivre(reservation.getExemplaire().getId_livre()));
        }

        modelAndView.addObject("utilisateur", utilisateur);
        modelAndView.addObject("reservations", reservations);

        return modelAndView;
    }

    @RequestMapping(value = "/gestion", method = RequestMethod.GET)
    public ModelAndView gestion(HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView("gestion_reservation");

        CompteBean compte = (CompteBean)request.getSession().getAttribute("compte");
        modelAndView.addObject("compte", compte);

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request); // 1
        if (!CollectionUtils.isEmpty(flashMap)) {
            List<ReservationBean> reservationByIdCompte = (List<ReservationBean>) flashMap.get("reservationByIdCompte");
            String erreur = (String) flashMap.get("erreur");
            modelAndView.addObject("erreur", erreur);

            for (ReservationBean reservation : reservationByIdCompte){
                reservation.setUtilisateur(utilisateurProxy.getUtilisateur(reservation.getId_utilisateur()));
                reservation.setDate_retour(dateDebutToFin(reservation.getDate_emprunt(), reservation.getExtension()));
                reservation.getExemplaire().setLivre(bibliothequeProxy.getLivre(reservation.getExemplaire().getId_livre()));

                //affichage
                reservation.setId_view_reservation(reservation.formatId(reservation.getId_reservation()));
                reservation.setId_view_exemplaire(reservation.formatId(reservation.getExemplaire().getid_exemplaire()));

            }
            modelAndView.addObject("reservationByIdCompte", reservationByIdCompte); // 2
        }

        List<ReservationBean> reservations;

        if(!reservationProxy.listeReservationsEnCours().isEmpty()) {
            reservations = reservationProxy.listeReservationsEnCours();

            //ajout des objets livres et utilisateurs ainsi que la date de retour dans les reservations
            for (ReservationBean reservation : reservations) {
                reservation.setUtilisateur(utilisateurProxy.getUtilisateur(reservation.getId_utilisateur()));
                reservation.setDate_retour(dateDebutToFin(reservation.getDate_emprunt(), reservation.getExtension()));
                reservation.getExemplaire().setLivre(bibliothequeProxy.getLivre(reservation.getExemplaire().getId_livre()));
                //affichage
                reservation.setId_view_reservation(reservation.formatId(reservation.getId_reservation()));
                reservation.setId_view_exemplaire(reservation.formatId(reservation.getExemplaire().getid_exemplaire()));
            }

            modelAndView.addObject("reservations", reservations);
        }
        return modelAndView;
    }

    @PostMapping(value = "/gestion/ajout")
    public ModelAndView ajout(String id_exemplaire, String id_utilisateur, RedirectAttributes redirectAttributes){

        int exemplaireId = Integer.parseInt(id_exemplaire);
        int utilisateurId = Integer.parseInt(id_utilisateur);
        ExemplaireBean exemplaire;

        String erreur = "";
        boolean isErreur = false;

        if(authentificationProxy.isCompte(utilisateurId)){
            if (reservationProxy.isExemplaire(exemplaireId)){
                exemplaire = reservationProxy.getExemplaire(exemplaireId);
                if (exemplaire.isDisponible()){

                    exemplaire.setDisponible(false);
                    ReservationBean reservation = new ReservationBean(utilisateurId, newDate(), exemplaire);
                    reservationProxy.newReservation(reservation);
                    reservationProxy.updateExemplaire(exemplaire);

                    log.info("Ajout pret " + reservation.toString());
                }else{
                    erreur = "L'exemplaire n'est pas disponible";
                    isErreur = true;
                }
            }else{
                erreur = "L'identifiant exemplaire n'existe pas";
                isErreur = true;
            }
        } else{
            erreur = "L'identifiant utilisateur n'existe pas";
            isErreur = true;
        }

        if(isErreur){
            redirectAttributes.addFlashAttribute("erreur", erreur);
        }

        return new ModelAndView("redirect:/gestion");
    }

    @PostMapping(value = "/gestion/retour")
    public ModelAndView retour(String id_reservation){

        log.info("Retour pret, id reservation : " + id_reservation);

        ReservationBean reservation = reservationProxy.getReservation(Integer.parseInt(id_reservation));
        reservation.setRendu(true);
        ExemplaireBean exemplaire = reservation.getExemplaire();
        exemplaire.setDisponible(true);
        reservationProxy.updateExemplaire(exemplaire);
        reservationProxy.updateReservation(reservation);

        return new ModelAndView("redirect:/gestion");
    }

    @PostMapping(value = "/gestion/extention")
    public ModelAndView extention(String id_reservation){

        log.info("Extention pret, id reservtion : " + id_reservation);

        ReservationBean reservation = reservationProxy.getReservation(Integer.parseInt(id_reservation));
        reservation.setExtension(true);
        reservationProxy.updateReservation(reservation);

        return new ModelAndView("redirect:/gestion");
    }

    @PostMapping(value = "/user/extention")
    public ModelAndView extentionUser(String id_reservation){

        log.info("Extention pret par l'utilisateur, id reservtion : " + id_reservation);

        ReservationBean reservation = reservationProxy.getReservation(Integer.parseInt(id_reservation));
        reservation.setExtension(true);
        reservationProxy.updateReservation(reservation);

        return new ModelAndView("redirect:/mes_emprunts");
    }

    @PostMapping(value = "/gestion/utilisateur")
    public ModelAndView byUser(String id_utilisateur, RedirectAttributes redirectAttributes){

        log.info("Par compte, id compte : " + id_utilisateur);

        List<ReservationBean> reservationByIdCompte = reservationProxy.getReservationById_compte(Integer.parseInt(id_utilisateur));
        System.out.println("gestion/utilisateur : " + reservationByIdCompte);
        //le retour
        redirectAttributes.addFlashAttribute("reservationByIdCompte", reservationByIdCompte);

        return new ModelAndView("redirect:/gestion");
    }

    //------------------------- METHODE INTERNE -------------------------

    /**
     * Renvoie la date du jour
     * @return java.sql.Date
     */
    private Date newDate(){
        LocalDate localDate = LocalDate.now();

        return Date.valueOf(localDate);
    }

    /**
     * Permet de calculer la date retour maximal en fonction de l'extention
     * @param dateDebut Date
     * @param extention Boolean
     * @return java.sql.Date
     */
    private Date dateDebutToFin(Date dateDebut, boolean extention){
        long dureePret = 28;
        LocalDate dateFin;

        if(!extention) {
            dateFin = dateDebut.toLocalDate().plusDays(dureePret);
        }else {
            dateFin = dateDebut.toLocalDate().plusDays(dureePret*2);
        }

        return Date.valueOf(dateFin);
    }
}
