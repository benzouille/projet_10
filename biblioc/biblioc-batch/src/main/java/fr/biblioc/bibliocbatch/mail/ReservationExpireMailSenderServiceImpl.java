package fr.biblioc.bibliocbatch.mail;

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Implementation de ReservationExpireMailSenderService permetant l'envoi de mail
 */
public class ReservationExpireMailSenderServiceImpl implements ReservationExpireMailSenderService {

    private final JavaMailSender javaMailSender;

    public ReservationExpireMailSenderServiceImpl(final JavaMailSender javaMailSender) {
        super();
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(final String mailDestination, final String content) throws MessagingException, MailSendException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setText(content, true);
        helper.setFrom("nePasRepondre@biblioc.com");
        helper.setTo(mailDestination);
        helper.setSubject("Durée de prêt dépassée");

        javaMailSender.send(message);
    }
}
