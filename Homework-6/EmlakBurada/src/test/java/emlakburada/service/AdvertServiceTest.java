package test.java.emlakburada.service;

import emlakburada.client.BannerClient;
import emlakburada.client.response.BannerResponse;
import emlakburada.dto.AdvertRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.model.Advert;
import emlakburada.model.User;
import emlakburada.model.enums.UserType;
import emlakburada.queue.QueueService;
import emlakburada.repository.AdvertRepository;
import emlakburada.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class AdvertServiceTest {

	@InjectMocks
	private AdvertService advertService;

	// --

	@Mock
	private AdvertRepository advertRepository;

	@Mock
	private BannerClient bannerClient;

	@Mock
	private QueueService rabbitMqService;

	@Mock
	private UserRepository userRepository;

	@Test()
	@DisplayName("when user not found throw exception. "
			+ "method name can be: should_throw_exception_when_user_not_found")
	void saveAdvertWithoutUserTest() {

		assertThrows(Exception.class, () -> {
			advertService.saveAdvert(prepareAdvertRequest());
		}, "İlan kaydedilemedi");

		// assertEquals("İlan kaydedilemedi", thrown.getMessage());

	}


	@Test
	void saveAdvert()  {

		AdvertRequest request = prepareAdvertRequest();

		User user = prepareUser();

		Mockito
				.when(userRepository.findById(request.getUserId()))
				.thenReturn(user);

		Mockito
		.when(advertRepository.save(any()))
		.thenReturn(prepareAdvert("başlık"));

		Mockito
		.when(bannerClient.saveBanner(any()))
		.thenReturn(new BannerResponse());



		AdvertResponse response = advertService.saveAdvert(request);

        assertNotNull(response);
		assertEquals("başlık", response.getTitle());
		verify(rabbitMqService).sendMessage(any());
		verify(bannerClient).saveBanner(any());

	}

	@Test
	void getAllAdverts() {


		Mockito.when(advertRepository.findAll()).thenReturn(prepareAdvertList());

		List<AdvertResponse> responseList = advertService.getAllAdverts();

		assertNotEquals(0, responseList.size());

		assertThat(responseList.size()).isNotZero();

		for (AdvertResponse response : responseList) {
			assertThat(response.getAdvertNo()).isEqualTo(0);

			assertEquals(Long.valueOf(12345), response.getPrice());

		}

	}

    @Test
    void updateAdvertbyId(){

        AdvertRequest request = prepareAdvertRequest();
		Advert advert =prepareAdvert("başlık");

		Mockito
				.when(advertRepository.findById(0))
				.thenReturn(advert);

        Mockito
                .when(advertRepository.save(advert))
                .thenReturn(prepareAdvert("başlık2"));




        AdvertResponse response = advertService.updateAdvertById(request,0);

        assertEquals("başlık2", response.getTitle());
        assertEquals(0, response.getAdvertNo());
        assertEquals(12345, response.getPrice());
    //    verify(rabbitMqService).sendMessage(any());
      //  verify(bannerClient).saveBanner(any());
    }

	@Test
	void deleteAdvertById(){
		Advert advert =prepareAdvert("başlık");

		Mockito
				.when(advertRepository.findById(0))
				.thenReturn(advert);

		String response = advertService.deleteAdvertById(0);
		Mockito.verify(advertRepository).deleteById(any());
		assertThat(response).isNotEmpty();


	}

    @Test
    void getAdvertByAdvertIdTest() {



        Mockito
                .when(advertRepository.findById(0))
                .thenReturn((prepareAdvert("başlık")));

        AdvertResponse response = advertService.findAdvertByAdvertId(0);
        assertNotNull(response);
        assertEquals("başlık", response.getTitle());
        assertEquals(0, response.getAdvertNo());
        assertEquals(12345, response.getPrice());

    }

	private List<Advert> prepareAdvertList() {
		List<Advert> adverts = new ArrayList<Advert>();
		adverts.add(prepareAdvert("başlık1"));
		adverts.add(prepareAdvert("başlık2"));
		adverts.add(prepareAdvert("başlık3"));
		return adverts;
	}

	private Advert prepareAdvert(String title) {
		Advert advert = new Advert();
		advert.setAdvertNo(0);
		advert.setTitle(title);
		advert.setPrice(Long.valueOf(12345));
		advert.setUser(new User(UserType.CORPORATE,"ahmet","dsadd@gmail.com"));
		return advert;
	}

	private User prepareUser() {
		User user = new User(UserType.CORPORATE, "mock name", "email");
		return user;
	}

	private AdvertRequest prepareAdvertRequest() {
		AdvertRequest request = new AdvertRequest();
		request.setUserId(5);
		request.setTitle("başlık");

		request.setPrice(Long.valueOf(12345));
		return request;
	}




}
