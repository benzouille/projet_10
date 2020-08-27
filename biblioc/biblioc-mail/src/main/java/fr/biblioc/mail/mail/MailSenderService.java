package fr.biblioc.mail.mail;

import javax.mail.MessagingException;

/**
 * Interface d'envoie de mail
 */
public interface MailSenderService {

    void send(String mailDestination,String titre, String content) throws MessagingException;
}
