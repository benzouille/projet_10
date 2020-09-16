package fr.biblioc.mail.mail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.junit.Rule;

import javax.mail.MessagingException;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MailSenderServiceImplTest {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String EMAIL = "test@test.com";
    private static final String BODY = "Some contents.";
    private static final String SUBJECT = "Some subject";

    @Mock
    private JavaMailSender javaMailSender;

    //@Autowired
    private MailSenderServiceImpl mailSender;

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        mailSender = new MailSenderServiceImpl(javaMailSender);
    }

    @Test
    public void send_should_return_exception() throws MessagingException {
        mailSender.setHtmlFormat(false);
        mailSender.send(EMAIL, SUBJECT, BODY);

        // Act
        //Mailbox.get(email);

        // Assert
        ArgumentCaptor<SimpleMailMessage> emailCaptor =
                ArgumentCaptor.forClass(SimpleMailMessage .class);
        verify(javaMailSender, times(1)).send(emailCaptor.capture());

        List<SimpleMailMessage> actualList = emailCaptor.getAllValues();
        collector.checkThat(actualList.size(), equalTo(1));
        collector.checkThat(actualList.get(0).getSubject(), equalTo(SUBJECT));
        collector.checkThat(actualList.get(0).getText(), equalTo(BODY));
    }

}
