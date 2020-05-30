package fr.biblioc.bibliocbatch.writer;

import fr.biblioc.bibliocbatch.mail.MailContentGenerator;
import fr.biblioc.bibliocbatch.mail.ReservationExpireMailSenderService;
import fr.biblioc.bibliocbatch.model.ReservationExpire;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * ItemWriter permetant la génération de contenu et l'envoi de mail
 */
public class ReservationExpireItemWriter implements ItemWriter<ReservationExpire> {

    private final ReservationExpireMailSenderService reservationExpireService;

    private final MailContentGenerator mailContentGenerator;

    public ReservationExpireItemWriter(final ReservationExpireMailSenderService reservationExpireService,
                                       final MailContentGenerator mailContentGenerator) {
        super();
        this.reservationExpireService = reservationExpireService;
        this.mailContentGenerator = mailContentGenerator;
    }

    @Override
    public void write(List<? extends ReservationExpire> reservations) throws Exception {
        for (ReservationExpire reservation : reservations) {
            String content = mailContentGenerator.generate(reservation);
            reservationExpireService.send(reservation.getEmail(), content);
        }
    }
}
