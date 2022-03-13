package main.java.emlakburada.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import emlakburada.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private UserType userType; // bireysel & kurumsal & yeniTip

	@Column(name = "name")
	private String name;

	@Column(name="email")
	private String email;

	@Column(name="password")
	private String password;

	@Column(name = "photo")
	private String photo;

	@Column(name = "biography")
	private String biography;

	@OneToMany(mappedBy = "user")
	private List<FavoriteAdvert>  favoriteAdverts;

	@OneToMany(mappedBy = "user")
	private List<Advert> postedAdverts;

	@OneToMany(mappedBy = "sender")
	private List<Message> messages;


	public User(UserType userType, String name, String email) {
		super();
		this.userType = userType;
		this.name = name;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
