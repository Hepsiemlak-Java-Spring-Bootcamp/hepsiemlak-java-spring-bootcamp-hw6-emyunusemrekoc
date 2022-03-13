package main.java.emlakburada.model;

import java.util.Date;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "title" )
	private String title;

	@Column(name = "message")
	private String message;

	@Column(name = "send_date")
	private Date sendDate;

	@Column(name = "read_date")
	private Date readDate;

	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User sender;

	@ManyToOne
	@JoinColumn(name = "user_id1",referencedColumnName = "id")
	private User receiver;

	public Message(String title, String message) {
		this.title = title;
		this.message = message;
	}
}
