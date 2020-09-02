package fr.biblioc.bibliocclientUi;

import fr.biblioc.bibliocclientUi.controller.GestionController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GestionControllerTests {

    @Autowired
    private GestionController gestionController;

    @Autowired
    private HttpServletRequest request;

    @Test
    public void empruntsUtilisateur_shouldReturn_AnModelAndView(){
        request.setAttribute("test", "un test");
         ModelAndView modelAndView = gestionController.empruntsUtilisateur(request);
        assertThat(modelAndView.getModel().get("test")).isEqualTo("un test");
    }
}
