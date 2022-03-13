package main.java.emlakburada.controller;

import emlakburada.dto.AdvertRequest;
import emlakburada.dto.MessageRequest;
import emlakburada.dto.UserRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.dto.response.MessageResponse;
import emlakburada.dto.response.UserResponse;
import emlakburada.model.Message;
import emlakburada.model.User;
import emlakburada.service.MessageService;
import emlakburada.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/messages")
    public ResponseEntity<MessageResponse> saveMessage(@RequestBody MessageRequest request) {
        return new ResponseEntity<>(messageService.saveMessage(request),HttpStatus.CREATED);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<MessageResponse>> findAll() {
        return new ResponseEntity<>(messageService.findAll(),HttpStatus.CREATED);

    }

    @PutMapping(value = "/messages/{id}")
    public ResponseEntity<MessageResponse> updateMessageById(@RequestBody MessageRequest request, @PathVariable int id) {
        return new ResponseEntity<>(messageService.updateMessageById(request, id), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/messages/{id}")
    public ResponseEntity<String> deleteMessageById(@PathVariable int id) {
        return new ResponseEntity<>(messageService.deleteMessageById(id), HttpStatus.CREATED);
    }


    @GetMapping(value = "/messages/{messageId}")
    public ResponseEntity<MessageResponse> findByMessageId(@PathVariable int messageId) {
        return new ResponseEntity<>(messageService.findByMessageId(messageId), HttpStatus.OK);
    }
}
