package src.test.java.emlakburada.service;

import emlakburada.config.EmailConfig;
import emlakburada.dto.EmailMessage;
import emlakburada.model.Email;
import emlakburada.repository.EmailRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private EmailConfig emailConfig;

    @Test
    void saveEmail(){

        Email email = prepareEmail("message");
        Mockito.when(emailRepository.save(email)).thenReturn(email);

        EmailMessage emailMessage =prepareEmailMessage("message");

        Email email1 = emailService.saveEmail(emailMessage);
        assertNotNull(email1);
        assertEquals(email1.getEmail(),emailMessage.getEmail());
        assertEquals(email1.getUserId(),emailMessage.getUserId());

    }

    @Test
    void send(){

        Mockito.when(emailConfig.getSmtpServer()).thenReturn("smtp.gmail.com");
        Mockito.when(emailConfig.getSmtpPort()).thenReturn("587");
        Mockito.when(emailConfig.getFrom()).thenReturn("emlakburada.patika@gmail.com");
        Mockito.when(emailConfig.getUsername()).thenReturn("username");
        Mockito.when(emailConfig.getPassword()).thenReturn("password");
        emailService.send("ahmet@gmail.com");

    }

    private EmailMessage prepareEmailMessage(String message) {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setEmail(message);
        emailMessage.setUserId(1);
        return  emailMessage;
    }

    private Email prepareEmail(String message) {
        Email email = new Email();
        email.setEmail(message);
        email.setUserId(1);
        return  email;
    }
}
