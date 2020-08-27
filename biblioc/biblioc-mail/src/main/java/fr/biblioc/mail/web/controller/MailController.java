package fr.biblioc.mail.web.controller;

import fr.biblioc.mail.mail.MailContentGenerator;
import fr.biblioc.mail.mail.MailContentGeneratorImpl;
import fr.biblioc.mail.mail.MailSenderService;
import fr.biblioc.mail.mail.MailSenderServiceImpl;
import fr.biblioc.mail.model.PrereservationDispoData;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
    public class MailController implements HealthIndicator {

        //------------------------- ATTRIBUTS -------------------------

        private MailContentGenerator mailContentGenerator;

        private MailSenderService mailSenderService;

        @Autowired
        private JavaMailSender javaMailSender;

        @Autowired
        private freemarker.template.Configuration config;

        Logger log = LoggerFactory.getLogger(this.getClass());

        //------------------------- METHODE -------------------------

        /**
         * Indique le status du microservice
         *
         * @return etat du microservice
         */
        @Override
        public Health health() {

            if (1+1 != 2) {
                return Health.down().build();
            }
            return Health.up().build();
        }

    /**
     * Envoyer un email
     *
     * @param prereservationDispoData bean {@link PrereservationDispoData}
     * @return ResponseEntity Reservation renvoi un http status.
     */
    @PostMapping(value = "/sendMailPrereservation")
    public ResponseEntity sendMailPrereservation(@RequestBody PrereservationDispoData prereservationDispoData) {

        try {
            mailContentGenerator = new MailContentGeneratorImpl(config);
            mailSenderService = new MailSenderServiceImpl(javaMailSender);

            String content = mailContentGenerator.generate(prereservationDispoData);

            mailSenderService.send(prereservationDispoData.getEmail(), "l'ouvrage " + prereservationDispoData.getTitre() + " est disponible.", content);
        } catch (IOException | TemplateException | MessagingException e) {
            e.printStackTrace();
        }

        log.info("mailSendPrereservation : " + prereservationDispoData);

        return new ResponseEntity("mail send",HttpStatus.CREATED);
    }

    @PostMapping(value = "/sendMailListPrereservation")
    public ResponseEntity sendMailPrereservationList(@RequestBody List<PrereservationDispoData> prereservationDispoDatas){

        try {

            mailContentGenerator = new MailContentGeneratorImpl(config);
            mailSenderService = new MailSenderServiceImpl(javaMailSender);

            for (PrereservationDispoData prereservationDispoData : prereservationDispoDatas) {

                String content = mailContentGenerator.generate(prereservationDispoData);

                mailSenderService.send(prereservationDispoData.getEmail(), "l'ouvrage " + prereservationDispoData.getTitre() + " est disponible.", content);

                log.info("mailSendPrereservation : " + prereservationDispoData);
            }

        } catch (TemplateNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity("mail send",HttpStatus.CREATED);
    }
}
