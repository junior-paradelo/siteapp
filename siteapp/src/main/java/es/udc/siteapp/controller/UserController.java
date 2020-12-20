package es.udc.siteapp.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.siteapp.model.User;
import es.udc.siteapp.repository.UserRepository;
import es.udc.siteapp.service.dto.UserDTO;

@RestController
public class UserController {

	@Autowired
	UserRepository userDAO;

	@GetMapping("users")
	public ResponseEntity<List<UserDTO>> findAllUsers() {
		List<User> findAll = this.userDAO.findAll();
		List<UserDTO> userDtoList = new LinkedList<>();
		for (int i = 0; i < findAll.size(); i++) {
			User user = findAll.get(i);
			UserDTO userDTO = new UserDTO(user);
			userDtoList.add(userDTO);
		}
		return ResponseEntity.ok(userDtoList);
	}

	@PostMapping("/users/{id}")
	public ResponseEntity<UserDTO> createUser(User user) {
		UserDTO userDTO = new UserDTO(userDAO.save(user));
		return ResponseEntity.ok(userDTO);
	}

	@PutMapping("user/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Long id, UserDTO userDto) {
		Optional<User> userOptional = userDAO.findById(id);
		if (userOptional.isPresent()) {
			User user = userDAO.getOne(id);
			user.setName(userDto.getName());
			user.setNickname(userDto.getNickname());
			user.setSurname(userDto.getSurname());
			return ResponseEntity.ok(userDto);
		} else {
			// Exception with error
			return ResponseEntity.noContent().build();
		}
	}
}
