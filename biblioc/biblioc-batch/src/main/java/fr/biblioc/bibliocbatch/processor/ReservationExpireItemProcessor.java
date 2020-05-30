package fr.biblioc.bibliocbatch.processor;

import fr.biblioc.bibliocbatch.model.ReservationExpire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * ItemProcessor de ReservationExpire
 */
public class ReservationExpireItemProcessor implements ItemProcessor<ReservationExpire, ReservationExpire> {

    private static final Logger log = LoggerFactory.getLogger(ReservationExpireItemProcessor.class);

    @Override
    public ReservationExpire process(final ReservationExpire re) throws Exception {
        final String bibliotheque = re.getBibliotheque().toUpperCase();

        final ReservationExpire transformedRe = new ReservationExpire(re.getId_reservation(), re.getId_exemplaire(), re.getEmail(), re.getPrenom(), bibliotheque, re.getTitre());

        return transformedRe;
    }

}