package es.udc.siteapp.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.siteapp.model.User;
import es.udc.siteapp.repository.UserRepository;
import es.udc.siteapp.service.dto.SiteDTO;
import es.udc.siteapp.service.dto.UserDTO;

public class UserService {

	@Autowired
	UserRepository userDAO;

	public List<UserDTO> findAll() {
		List<User> findAll = userDAO.findAll();
		List<UserDTO> userDtoList = new LinkedList<>();
		for (int i = 0; i < findAll.size(); i++) {
			User user = findAll.get(i);
			UserDTO userDTO = new UserDTO(user);
			userDtoList.add(userDTO);
		}
		return userDtoList;
	}

	public void registerUser(String name, String surname, String nickname, String email, String password) {
		User user = new User(name, surname, nickname, email, password);
		userDAO.save(user);
	}

	public UserDTO getUserById(Long id) {
		UserDTO userDTO = null;
		Optional<User> optionalUser = userDAO.findById(id);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			userDTO = new UserDTO(user);
		}
		return userDTO;
	}

	public UserDTO getUserByName(String name) {
		User user = userDAO.findUserByName(name);
		return new UserDTO(user);
	}

	public User getCompleteUserById(Long id) {
		return userDAO.findById(id).orElse(null);
	}

	public UserDTO updateUser(UserDTO userDto) {
		UserDTO userDtoResponse = null;
		Optional<User> optionalUser = userDAO.findById(userDto.getId());
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setName(userDto.getName());
			user.setNickname(userDto.getNickname());
			user.setEmail(userDto.getEmail());
			User userSave = userDAO.save(user);
			userDtoResponse = new UserDTO(userSave);
		}
		return userDtoResponse;
	}

	public void deleteById(Long id) {
		userDAO.deleteById(id);
	}

	public List<SiteDTO> findFavourited(Long id) {
		return null;
	}

}
