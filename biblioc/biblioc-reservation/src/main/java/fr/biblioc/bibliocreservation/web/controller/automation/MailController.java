package fr.biblioc.bibliocreservation.web.controller.automation;

import fr.biblioc.bibliocreservation.dto.ExemplaireDto;
import fr.biblioc.bibliocreservation.dto.ListeAttenteDto;
import fr.biblioc.bibliocreservation.model.Exemplaire;
import fr.biblioc.bibliocreservation.model.PreReservation;
import fr.biblioc.bibliocreservation.model.PrereservationDispoDataBean;
import fr.biblioc.bibliocreservation.web.controller.ExemplaireController;
import fr.biblioc.bibliocreservation.web.controller.ListeAttenteController;
import fr.biblioc.bibliocreservation.web.controller.PreReservationController;
import fr.biblioc.bibliocreservation.web.controller.automation.model.CompteBean;
import fr.biblioc.bibliocreservation.web.controller.automation.model.UtilisateurBean;
import fr.biblioc.bibliocreservation.web.controller.automation.proxies.BibliocAuthentificationProxy;
import fr.biblioc.bibliocreservation.web.controller.automation.proxies.BibliocBibliothequeProxy;
import fr.biblioc.bibliocreservation.web.controller.automation.proxies.BibliocMailProxy;
import fr.biblioc.bibliocreservation.web.controller.automation.proxies.BibliocUtilisateurProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller utilisant le proxy vers le microservice reservation
 */
@Controller
public class MailController {

    //------------------------- PARAMETRE -------------------------

    @Autowired
    private BibliocMailProxy mailProxy;

    @Autowired
    private BibliocAuthentificationProxy authentificationProxy;

    @Autowired
    private BibliocUtilisateurProxy utilisateurProxy;

    @Autowired
    private BibliocBibliothequeProxy bibliothequeProxy;

    @Autowired
    private PreReservationController preReservationController;

    @Autowired
    private ExemplaireController exemplaireController;

    @Autowired
    private ListeAttenteController listeAttenteController;

    //------------------------- METHODE -------------------------

    public void inspectionDelais() {

        //recuperer la liste de prereservation en attente de réponse expiré
        List<PreReservation> preReservationList = preReservationController.getExpiredMailSendPreReservation();

        //liste des données d'envoi de mail
        List<PrereservationDispoDataBean> prereservationDispoDataList = new ArrayList<>();

        if (!preReservationList.isEmpty()) {
            for (PreReservation preReservation : preReservationList) {
                preReservation.setExpire(true);
                preReservationController.updatePreReservation(preReservation);
                ListeAttenteDto listeAttenteDto = listeAttenteController.listAttenteById_liste_attente(preReservation.getId_prereservation());
                ExemplaireDto exemplaire = exemplaireController.recupererUnExemplaire(preReservation.getId_exemplaire());
                String titre = bibliothequeProxy.getTitreLivre(exemplaire.getId_livre());

                //Vérification de l'état de la liste d'attente
                if (!listeAttenteDto.getPreReservationList().isEmpty()) {

                    //suivant de la liste
                    PreReservation nextPreReservation = listeAttenteDto.getPreReservationList().get(0);
                    CompteBean compte = authentificationProxy.getCompte(preReservation.getId_compte());
                    UtilisateurBean utilisateur = utilisateurProxy.getUtilisateur(compte.getId_utilisateur());


                    //envoi de mail
                    //TODO faire une liste de PrereservationDispoDataBean
                    PrereservationDispoDataBean prereservationDispoData = new PrereservationDispoDataBean(
                            preReservation.getId_exemplaire(),
                            compte.getEmail(),
                            utilisateur.getPrenom(),
                            exemplaire.getBibliotheque().getNom(),
                            titre);
                    prereservationDispoDataList.add(prereservationDispoData);

                    //mise a jour de la prereservation avec date envoi mail et l'id de l'exemplaire en plus
                    nextPreReservation.setId_exemplaire(preReservation.getId_exemplaire());
                    nextPreReservation.setDateMailSend(newDate());
                    preReservationController.updatePreReservation(nextPreReservation);

                } else {
                    exemplaire.setDisponible(true);
                    exemplaireController.updateExemplaire(exemplaireController.getExemplaireFromDto(exemplaire));
                }

            }

            mailProxy.sendMailPrereservationList(prereservationDispoDataList);
        }
    }

    //------------------------- METHODE INTERNE-------------------------

    /**
     * Renvoie la date du jour
     *
     * @return java.sql.Date
     */
    private Date newDate() {
        LocalDate localDate = LocalDate.now();

        return Date.valueOf(localDate);
    }

}
