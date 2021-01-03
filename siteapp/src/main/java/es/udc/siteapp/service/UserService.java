package es.udc.siteapp.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.udc.siteapp.exception.ResourceNotFoundException;
import es.udc.siteapp.model.Authority;
import es.udc.siteapp.model.AuthorityName;
import es.udc.siteapp.model.User;
import es.udc.siteapp.repository.AuthorityRepository;
import es.udc.siteapp.repository.UserRepository;
import es.udc.siteapp.service.dto.SiteDTO;
import es.udc.siteapp.service.dto.UserDTO;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthorityRepository authorityRepository;

	@Autowired
	PasswordEncoder bcrypt;

	public List<UserDTO> findAll() {
		List<User> findAll = userRepository.findAll();
		List<UserDTO> userDtoList = new LinkedList<>();
		for (int i = 0; i < findAll.size(); i++) {
			User user = findAll.get(i);
			UserDTO userDTO = new UserDTO(user);
			userDtoList.add(userDTO);
		}
		return userDtoList;
	}

	public UserDTO getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		return new UserDTO(user);
	}

	public UserDTO getUserByName(String name) {
		User user = userRepository.findByUsername(name);
		if (user == null) {
			throw new ResourceNotFoundException("User not found with name: " + name);
		}
		return new UserDTO(user);
	}

	public User getCompleteUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Complete user not found with id: " + id));
	}

	public User registerUser(String username, String password, String email, String firstname, String lastname,
			Boolean admin) {
		Authority authority;
		if (Boolean.TRUE.equals(admin)) {
			authority = authorityRepository.findAuthorityByName(AuthorityName.ROLE_ADMIN);
		} else {
			authority = authorityRepository.findAuthorityByName(AuthorityName.ROLE_USER);
		}
		User user = new User(username, bcrypt.encode(password), firstname, lastname, email, new Date(), new Date(),
				authority);
		return userRepository.save(user);
	}

	public UserDTO updateUser(UserDTO userDto) {
		UserDTO userDtoResponse = null;
		User user = userRepository.findById(userDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userDto.getId()));

		user.setFirstname(userDto.getFirstname());
		user.setLastname(userDto.getLastname());
		user.setEmail(userDto.getEmail());
		User userSave = userRepository.save(user);
		userDtoResponse = new UserDTO(userSave);

		return userDtoResponse;
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	public List<SiteDTO> findFavourited(Long id) {
		return null;
	}

}
