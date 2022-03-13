package src.test.java.emlakburada.service;

import emlakburada.dto.EmailMessage;
import emlakburada.model.Email;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class RabbitMqListenerServiceTest {

    @InjectMocks
    private RabbitMqListenerService rabbitMqListenerService;

    @Mock
    private EmailService emailService;

    @Test
    void receiveMessage(){

        EmailMessage emailMessage =prepareEmailMessage("message");

        rabbitMqListenerService.receiveMessage(emailMessage);
        verify(emailService).send(emailMessage.getEmail());
        verify(emailService).saveEmail(emailMessage);

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
