package main.java.emlakburada.dto.response;

import emlakburada.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    private int id;
    private String title;
    private String message;
    private Date sentDate;
    private Date readDate;
    private boolean statusOfRead;
//    private User sender;
//    private User receiver;
}
