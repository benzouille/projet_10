package fr.biblioc.mail.mail;

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Implementation de ReservationExpireMailSenderService permetant l'envoi de mail
 */
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender javaMailSender;
    private Boolean html = true;

    public MailSenderServiceImpl(final JavaMailSender javaMailSender) {
        super();
        this.javaMailSender = javaMailSender;
    }

    public void setHtmlFormat(Boolean html){
        this.html = html;
    }

    @Override
    public void send(final String mailDestination, final String titre, final String content) throws MessagingException, MailSendException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setText(content, html);
        helper.setFrom("nePasRepondre@biblioc.com");
        helper.setTo(mailDestination);
        helper.setSubject(titre);

        javaMailSender.send(message);
    }
}
