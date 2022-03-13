package main.java.emlakburada.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailMessage {

	private int userId;
	private String email;

}
