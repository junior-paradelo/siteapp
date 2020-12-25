package es.udc.siteapp.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.siteapp.exception.ResourceNotFoundException;
import es.udc.siteapp.model.User;
import es.udc.siteapp.model.UserAuthority;
import es.udc.siteapp.repository.UserRepository;
import es.udc.siteapp.service.dto.SiteDTO;
import es.udc.siteapp.service.dto.UserDTO;

@Service
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

	public User registerUser(String name, String surname, String nickname, String email, String password) {
		User user = new User(name, surname, nickname, email, password, UserAuthority.ROLE_USER, false);
		return userDAO.save(user);
	}

	public UserDTO getUserById(Long id) {
		User user = userDAO.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		return new UserDTO(user);
	}

	public UserDTO getUserByName(String name) {
		User user = userDAO.findByName(name);
		if (user == null) {
			throw new ResourceNotFoundException("User not found with name: " + name);
		}
		return new UserDTO(user);
	}

	public User getCompleteUserById(Long id) {
		return userDAO.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Complete user not found with id: " + id));
	}

	public UserDTO updateUser(UserDTO userDto) {
		UserDTO userDtoResponse = null;
		User user = userDAO.findById(userDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userDto.getId()));

		user.setName(userDto.getName());
		user.setNickname(userDto.getNickname());
		user.setEmail(userDto.getEmail());
		User userSave = userDAO.save(user);
		userDtoResponse = new UserDTO(userSave);

		return userDtoResponse;
	}

	public void deleteById(Long id) {
		userDAO.deleteById(id);
	}

	public List<SiteDTO> findFavourited(Long id) {
		return null;
	}

}
