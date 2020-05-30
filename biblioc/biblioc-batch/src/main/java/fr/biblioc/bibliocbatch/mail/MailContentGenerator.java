package fr.biblioc.bibliocbatch.mail;

import fr.biblioc.bibliocbatch.model.ReservationExpire;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Interface Generateur mail
 */
public interface MailContentGenerator {

    String generate(ReservationExpire reservationExpire) throws TemplateException, IOException;
}
