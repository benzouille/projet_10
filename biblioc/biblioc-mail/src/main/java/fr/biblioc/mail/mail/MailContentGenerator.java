package fr.biblioc.mail.mail;

import fr.biblioc.mail.model.PrereservationDispoData;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Interface Generateur mail
 */
public interface MailContentGenerator {

    String generate(PrereservationDispoData prereservationDispoData) throws TemplateException, IOException;
}
