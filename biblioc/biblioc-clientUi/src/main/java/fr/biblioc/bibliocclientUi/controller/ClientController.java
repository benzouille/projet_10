package fr.biblioc.bibliocclientUi.controller;

import fr.biblioc.bibliocclientUi.beans.authentification.CompteBean;
import fr.biblioc.bibliocclientUi.beans.reservation.BibliothequeBean;
import fr.biblioc.bibliocclientUi.proxies.BibliocReservationProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller de la page d'accueil
 */
@Controller
public class ClientController {

    //------------------------- PARAMETRE -------------------------

    @Autowired
    private BibliocReservationProxy reservationProxy;

    //------------------------- METHODE -------------------------

    /**
     * Reuquete de la page d'accueil
     * @param request
     * @param redirectAttributes
     * @return page d'accueil
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView accueilConnecte(HttpServletRequest request, RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView("accueil");

        List<BibliothequeBean> bibliotheques = reservationProxy.listBibliotheques();
        modelAndView.addObject("bibliotheques", bibliotheques);

        CompteBean compte = (CompteBean)request.getSession().getAttribute("compte");
        modelAndView.addObject("compte", compte);

        return modelAndView;
    }
}
