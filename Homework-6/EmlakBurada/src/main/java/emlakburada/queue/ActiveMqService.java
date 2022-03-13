package main.java.emlakburada.queue;

import emlakburada.service.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqService implements QueueService{

	@Autowired
	private JmsTemplate activeTemplate;


	@Override
	public void sendMessage(String message) {
		activeTemplate.convertAndSend("emlakburada.queue", message.toString());

	}
}
