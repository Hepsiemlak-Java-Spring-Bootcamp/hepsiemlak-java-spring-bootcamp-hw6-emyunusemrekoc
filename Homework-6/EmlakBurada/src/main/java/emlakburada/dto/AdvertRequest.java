package main.java.emlakburada.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import emlakburada.model.AdvertPhoto;
import emlakburada.model.RealEstate;
import emlakburada.model.User;
import lombok.Data;

@Data
public class AdvertRequest {

	private String title;
	private int userId; // hem bireysel & kurumsal
	private Long price;

}
