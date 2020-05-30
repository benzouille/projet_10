package fr.biblioc.bibliocbatch.configurations;

import fr.biblioc.bibliocbatch.listener.JobCompletionNotificationListener;
import fr.biblioc.bibliocbatch.mail.MailContentGenerator;
import fr.biblioc.bibliocbatch.mail.MailContentGeneratorImpl;
import fr.biblioc.bibliocbatch.mail.ReservationExpireMailSenderService;
import fr.biblioc.bibliocbatch.mail.ReservationExpireMailSenderServiceImpl;
import fr.biblioc.bibliocbatch.model.ReservationExpire;
import fr.biblioc.bibliocbatch.processor.ReservationExpireItemProcessor;
import fr.biblioc.bibliocbatch.writer.ReservationExpireItemWriter;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Classe de configuration de spring batch avec les beans de job step reader et writer
 */
@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    //------------------------- ATTRIBUT -------------------------

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private freemarker.template.Configuration config;

    private static final Logger log = LoggerFactory.getLogger(SpringBatchConfig.class);

    //------------------------- BEAN -------------------------

    /**
     * Reader json
     * @return JsonItemReaderBuilder de ReservationExpire
     * @throws MalformedURLException
     */
    @Bean
    @StepScope
    public JsonItemReader<ReservationExpire> reservationExpireItemReader() throws MalformedURLException {
        URL resource = new URL("http://localhost:9003//Reservations/expire");
        return new JsonItemReaderBuilder<ReservationExpire>()
                .name("reservationItemReader")
                .resource(new UrlResource(resource))
                .jsonObjectReader(new JacksonJsonObjectReader<>(ReservationExpire.class))
                .build();
    }

    @Bean
    public ReservationExpireItemProcessor processor() {
        return new ReservationExpireItemProcessor();
    }

    @Bean
    public MailContentGenerator mailContentGenerator(final freemarker.template.Configuration conf)
            throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
        return new MailContentGeneratorImpl(conf);
    }

    @Bean
    public ReservationExpireMailSenderService planningMailSenderService(final JavaMailSender javaMailSender) {
        return new ReservationExpireMailSenderServiceImpl(javaMailSender);
    }

    @Bean
    public ReservationExpireItemWriter reservationExpireItemWriter(final ReservationExpireMailSenderService reservationExpireService,
                                                                   final MailContentGenerator mailContentGenerator) {
        return new ReservationExpireItemWriter(reservationExpireService, mailContentGenerator);
    }

    @Bean
    public Step step1(ItemWriter<ReservationExpire> reservationExpireItemWriter) throws MalformedURLException {
        return stepBuilderFactory.get("step1")
                .<ReservationExpire, ReservationExpire>chunk(10)
                .reader(reservationExpireItemReader())
                .processor(processor())
                .writer(reservationExpireItemWriter)
                .build();
    }

    @Bean
    public Job importReservationExpireJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importReservationExpireJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }
}
