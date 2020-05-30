package fr.biblioc.bibliocbatch.mail;

import javax.mail.MessagingException;

/**
 * Interface d'envoie de mail
 */
public interface ReservationExpireMailSenderService {

    void send(String mailDestination, String content) throws MessagingException;
}
