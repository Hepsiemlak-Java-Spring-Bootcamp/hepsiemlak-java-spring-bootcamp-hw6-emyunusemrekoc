package main.java.emlakburada.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Banner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	@Column(name = "advert_no")
	private int advertNo;
	@Column(name = "phone")
	private String phone;
	@Column(name = "total")
	private int total;
	//private Address address;


	public Banner(int advertNo, String phone, int total) {
		this.advertNo = advertNo;
		this.phone = phone;
		this.total = total;
	}
}
