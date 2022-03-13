package test.java.emlakburada.service;
import emlakburada.dto.MessageRequest;
import emlakburada.dto.response.MessageResponse;
import emlakburada.model.Message;
import emlakburada.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;


@SpringBootTest
public class MessageServiceTest {

    @InjectMocks
    private MessageService messageService;

    @Mock
    private MessageRepository messageRepository;

    @BeforeEach
    void setup() {


        Mockito
                .when(messageRepository.findAll())
                .thenReturn(prepareMockMessageList());
    }

    @Test
    void findAll(){

        List<MessageResponse> allMessages = messageService.findAll();

        assertNotNull(allMessages);

        assertThat(allMessages.size()).isNotZero();

    }

    @Test
    void saveMessage() {


        Mockito
                .when(messageRepository.save(any()))
                .thenReturn(prepareMessage("başlık1"));

        MessageResponse response= messageService.saveMessage(prepareMessageRequest());
        assertNotNull(response);
        assertEquals("başlık1",response.getTitle());
        assertEquals("mesaj",response.getMessage());

    }

    @Test
    void updateMessagebyId(){

        MessageRequest request = prepareMessageRequest();
        Message message =prepareMessage("başlık");

        Mockito
                .when(messageRepository.findById(0))
                .thenReturn(message);

        Mockito
                .when(messageRepository.save(message))
                .thenReturn(prepareMessage("başlık2"));




        MessageResponse response = messageService.updateMessageById(request,0);

        assertEquals("başlık2", response.getTitle());
        assertEquals("mesaj", response.getMessage());

    }

    @Test
    void deleteMessageById(){
        Message message =prepareMessage("başlık");

        Mockito
                .when(messageRepository.findById(0))
                .thenReturn(message);

        String response = messageService.deleteMessageById(anyInt());
        Mockito.verify(messageRepository).deleteById(any());
        assertThat(response).isNotEmpty();


    }

    @Test
    void findByMessageIdTest() {



        Mockito
                .when(messageRepository.findById(0))
                .thenReturn((prepareMessage("başlık")));

        MessageResponse response = messageService.findByMessageId(0);
        assertNotNull(response);
        assertEquals("başlık", response.getTitle());
        assertEquals("mesaj", response.getMessage());

    }

    private MessageRequest prepareMessageRequest() {
        return new MessageRequest("baslik", "mesaj");
    }

    private Message prepareMessage(String baslik) {
        return new Message(baslik, "mesaj");

    }

    private List<Message> prepareMockMessageList() {
        List<Message> messageList = new ArrayList<>();
        messageList.add(prepareMessage("başlık1"));
        messageList.add(prepareMessage("başlık2"));
        messageList.add(prepareMessage("başlık3"));
        return messageList;
    }
}
