package main.java.emlakburada.service;

import emlakburada.dto.AdvertRequest;
import emlakburada.dto.MessageRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.dto.response.MessageResponse;
import emlakburada.model.Advert;
import emlakburada.model.Message;
import emlakburada.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    private static int messageId = 0;

    public MessageResponse saveMessage(MessageRequest request) {
        Message savedMessage = messageRepository.save(convertToMessage(request));

        return convertToMessageResponse(savedMessage);
    }

    public List<MessageResponse> findAll() {

        List<MessageResponse> messageList = new ArrayList<>();
        for (Message message : messageRepository.findAll()) {
            messageList.add(convertToMessageResponse(message));
        }
        return messageList;

    }

    public MessageResponse findByMessageId(int messageId) {
        Message message = messageRepository.findById(messageId);
        return convertToMessageResponse(message);
    }

    public MessageResponse updateMessageById(MessageRequest request, int messageId) {

        Message message = messageRepository.findById(messageId);
        message.setMessage(request.getMessage());
        message.setTitle(request.getTitle());

        Message savedMessage = messageRepository.save(message);

        return convertToMessageResponse(savedMessage);

    }

    public String deleteMessageById(int messageId) {
        Message message = messageRepository.findById(messageId);
        if (message == null) {
            return "bu id ye ait mesaj bulunamadı";
        } else {
            String info = message.getTitle() + " başlıklı mesaj silindi.";
            messageRepository.deleteById(messageId);
            return info;

        }

    }
    private MessageResponse convertToMessageResponse(Message savedMessage) {
        MessageResponse response = new MessageResponse();
        response.setMessage((savedMessage.getMessage()));
        response.setTitle(savedMessage.getTitle());
        response.setId(savedMessage.getId());

        return response;
    }

    private Message convertToMessage(MessageRequest messageRequest) {
        Message message = new Message();
        messageId++;

        message.setId(messageId);
        message.setMessage(messageRequest.getMessage());
        message.setTitle(messageRequest.getTitle());

        return message;
    }

}
