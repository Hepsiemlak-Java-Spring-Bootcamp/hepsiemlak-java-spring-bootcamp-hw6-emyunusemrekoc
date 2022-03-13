package test.java.emlakburada.service;

import emlakburada.dto.AdvertRequest;
import emlakburada.dto.UserRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.dto.response.UserResponse;
import emlakburada.model.Advert;
import emlakburada.model.User;
import emlakburada.model.enums.UserType;
import emlakburada.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@BeforeEach
	void setup() {

		//// @formatter:off
		Mockito
		.when(userRepository.findAll())
		.thenReturn(prepareMockUserList());

		// @formatter:on

	}

	private List<User> prepareMockUserList() {
		List<User> userList = new ArrayList<User>();
		userList.add(new User(UserType.INDIVIDUAL, "cem", "cem@patika.com"));
		userList.add(new User(UserType.INDIVIDUAL, "emre", "emre@patika.com"));
		return userList;
	}

	@Test
	void findAll() {

		List<UserResponse> allUser = userService.findAll();

		assertNotNull(allUser);

		assertThat(allUser.size()).isNotZero();
	}

	@Test
	void saveUser() {


		Mockito
				.when(userRepository.save(any()))
				.thenReturn(prepareUser("ahmet"));

		UserResponse response =userService.saveUser(prepareUserRequest());
		assertNotNull(response);
		assertEquals("ahmet",response.getName());
		assertEquals("dsadd@gmail.com",response.getEmail());



	}

	@Test
	void updateUser() {

		UserRequest request = prepareUserRequest();
		User user =prepareUser("ahmet");

		Mockito
				.when(userRepository.findById(0))
				.thenReturn(user);

		Mockito
				.when(userRepository.save(user))
				.thenReturn(prepareUser("ahmet2"));




		UserResponse response = userService.updateUserById(request,0);

		assertEquals("ahmet2", response.getName());


	}

	@Test
	void deleteUserById(){
		User user =prepareUser("ahmet");

		Mockito
				.when(userRepository.findById(0))
				.thenReturn(user);


		String response = userService.deleteAdvertById(0);
		Mockito.verify(userRepository).deleteById(0);
		assertThat(response).isNotEmpty();


	}

	@Test
	void getAdvertByAdvertIdTest() {





		Mockito
				.when(userRepository.findById(0))
				.thenReturn((prepareUser("ahmet")));

		UserResponse response = userService.findByUserId(0);
		assertNotNull(response);
		assertEquals("ahmet", response.getName());

	}


	private UserRequest prepareUserRequest() {
		return new UserRequest("ahmet", "ahmet@gmail.com", "", null, null);
	}

	private User prepareUser(String name) {
	 return new User(UserType.CORPORATE,name,"dsadd@gmail.com");

	}
}
