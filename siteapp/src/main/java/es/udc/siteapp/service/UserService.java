package es.udc.siteapp.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.udc.siteapp.exception.ResourceNotFoundException;
import es.udc.siteapp.model.Authority;
import es.udc.siteapp.model.AuthorityName;
import es.udc.siteapp.model.User;
import es.udc.siteapp.repository.AuthorityRepository;
import es.udc.siteapp.repository.UserRepository;
import es.udc.siteapp.service.dto.UserDTO;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private PasswordEncoder bcrypt;

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

	public User registerUser(String username, String password, String email, String firstname, String lastname,
			Boolean admin) {
		Authority authority;
		if (Boolean.TRUE.equals(admin)) {
			authority = authorityRepository.findAuthorityByName(AuthorityName.ROLE_ADMIN);
		} else {
			authority = authorityRepository.findAuthorityByName(AuthorityName.ROLE_USER);
		}
		User user = new User(username, bcrypt.encode(password), firstname, lastname, email, authority, null);
		return userRepository.save(user);
	}

	public void uploadImage(Long id, MultipartFile file) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			try {
				user.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
			} catch (IOException e) {
				throw new ResourceNotFoundException("Error to insert image");
			}
			userRepository.save(user);
		}
	}

	public String getImage(Long id) {
		User user = userRepository.getOne(id);
		return user.getImage();
	}

	public UserDTO updateUser(Long id, UserDTO userDto) {
		UserDTO userDtoResponse = null;
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userDto.getId()));

		if (StringUtils.isNotBlank(userDto.getFirstname())) {
			user.setFirstname(userDto.getFirstname());
		}
		if (StringUtils.isNotBlank(userDto.getLastname())) {
			user.setLastname(userDto.getLastname());
		}
		if (StringUtils.isNotBlank(userDto.getEmail())) {
			user.setEmail(userDto.getEmail());
		}
		if (userDto.getEnabled() != null) {
			user.setEnabled(userDto.getEnabled());
		}
		User userSave = userRepository.save(user);
		userDtoResponse = new UserDTO(userSave);

		return userDtoResponse;
	}

	public void deleteById(Long id) {
//		userRepository.deleteById(id);
		User user = userRepository.findById(id).get();
		user.setEnabled(false);
		userRepository.save(user);
	}

	public List<Authority> findAllAuthorities() {
		return authorityRepository.findAll();
	}

	public UserDTO changePassword(UserDTO userDto) {
		User user = userRepository.findByUsername(userDto.getUsername());
		User userSave = null;
		if (user.getUsername().equalsIgnoreCase(userDto.getUsername())
				&& user.getEmail().equalsIgnoreCase(userDto.getEmail())) {
			user.setPassword(bcrypt.encode(userDto.getPassword()));
			user.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
			userSave = userRepository.save(user);
		}
		return new UserDTO(userSave);
	}
}
