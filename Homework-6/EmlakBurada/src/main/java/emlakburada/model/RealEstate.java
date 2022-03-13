package main.java.emlakburada.model;

import emlakburada.model.enums.HousingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RealEstate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@OneToOne()
	@JoinColumn(name = "adress_id",referencedColumnName = "id")
	private Address adres;

	@Column(name = "housing_type")
	private HousingType housingType;

	@Column(name = "squaremeters")
	private String squareMeters ;



}