package main.java.emlakburada.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Advert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "advert_no")
	private int advertNo;

	@OneToOne
	@JoinColumn(name = "real_estate_id",referencedColumnName = "id")
	private RealEstate realEstate;

	@Column(name = "title")
	private String title;

	@ManyToOne()
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User user; // hem bireysel & kurumsal

	@OneToMany(mappedBy = "advert")
	private List<AdvertPhoto> advertPhotos;

	@Column(name = "price")
	private Long price;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "turbo")
	private boolean turbo;

	@Column(name = "is_active")
	private boolean isActive;

}
