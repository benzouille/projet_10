package fr.biblioc.mail.mail;


import fr.biblioc.mail.model.PrereservationDispoData;
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
        template = conf.getTemplate("mailPrereservationDispo.ftl");
    }

    @Override
    public String generate(PrereservationDispoData prereservationDispoData) throws TemplateException, IOException {
        StringWriter sw = new StringWriter();
        template.process(prereservationDispoData, sw);
        return sw.toString();
    }
}
