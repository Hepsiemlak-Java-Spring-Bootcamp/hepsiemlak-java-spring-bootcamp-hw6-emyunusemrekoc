package main.java.emlakburada.dto.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import emlakburada.model.AdvertPhoto;
import emlakburada.model.RealEstate;
import emlakburada.model.User;
import lombok.Data;

import javax.persistence.*;

@Data
public class AdvertResponse {

	private int advertNo;
	//private RealEstate realEstate;
	private String title;
	//@JsonIgnore
	//private User user; // hem bireysel & kurumsal
	//private List<AdvertPhoto> advertPhotos;
	private Long price;
	private Date createdDate;
	private Date endDate;
	private boolean turbo;
	private boolean isActive;

}
