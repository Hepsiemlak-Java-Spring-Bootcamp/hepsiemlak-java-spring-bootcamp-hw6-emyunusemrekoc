package main.java.emlakburada.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import emlakburada.dto.MessageRequest;
import emlakburada.dto.response.MessageResponse;
import emlakburada.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emlakburada.dto.UserRequest;
import emlakburada.dto.response.UserResponse;
import emlakburada.model.User;
import emlakburada.repository.UserRepository;

@Service
public class UserService extends UserBaseService {

	@Autowired
	private UserRepository userRepository;

	private static int userId = 0;

	public UserResponse saveUser(UserRequest request) {
		User savedUser = userRepository.save(convertToUserEntity(request));

		return convertToUserResponse(savedUser);
	}

	public List<UserResponse> findAll() {

		List<UserResponse> userList = new ArrayList<>();
		for (User user : userRepository.findAll()) {
			userList.add(convertToUserResponse(user));
		}
		return userList;

	}

	public UserResponse updateUserById(UserRequest request, int userId) {

		User user = userRepository.findById(userId);
		user.setBiography(request.getBiography());
		user.setEmail(request.getEmail());
		user.setName(request.getName());
		user.setPhoto(request.getPhoto());


		User savedUser = userRepository.save(user);

		return convertToUserResponse(savedUser);

	}

	public String deleteAdvertById(int userId) {
		User user = userRepository.findById(userId);
		if (user == null) {
			return "bu id ye ait kullanıcı bulunamadı";
		} else {
			String info = user.getName() + " isimli kullanıcı silindi.";
			userRepository.deleteById(userId);
			return info;

		}

	}

	public UserResponse findByUserId(int userId) {
		User user = userRepository.findById(userId);
		return convertToUserResponse(user);
	}

}
