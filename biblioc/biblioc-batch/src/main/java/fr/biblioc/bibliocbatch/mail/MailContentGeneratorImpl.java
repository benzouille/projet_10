package fr.biblioc.bibliocbatch.mail;


import fr.biblioc.bibliocbatch.model.ReservationExpire;
import freemarker.core.ParseException;
import freemarker.template.*;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Implementation de MailContentGenerator permetant de fusionner les données et le template du mail
 */
public class MailContentGeneratorImpl implements MailContentGenerator {

    private final Template template;

    public MailContentGeneratorImpl(final Configuration conf)
            throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
        super();
        template = conf.getTemplate("mail.ftl");
    }

    @Override
    public String generate(ReservationExpire reservationExpire) throws TemplateException, IOException {
        StringWriter sw = new StringWriter();
        template.process(reservationExpire, sw);
        return sw.toString();
    }
}
