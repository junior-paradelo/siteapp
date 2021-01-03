package es.udc.siteapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.siteapp.model.Authority;
import es.udc.siteapp.model.User;
import es.udc.siteapp.security.JwtUser;
import es.udc.siteapp.security.JwtUserFactory;
import es.udc.siteapp.service.UserService;
import es.udc.siteapp.service.dto.UserDTO;

@RestController
@RequestMapping("/api/")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("users")
	public List<UserDTO> findAllUsers() {
		return userService.findAll();
	}

	@GetMapping("users/{id}")
	public UserDTO getUserById(@PathVariable(value = "id") Long id) {
		return userService.getUserById(id);
	}

	@PostMapping("users")
	public JwtUser createUser(@RequestBody UserDTO userDto) {
		User registerUser = userService.registerUser(userDto.getUsername(), userDto.getPassword(), userDto.getEmail(),
				userDto.getFirstname(), userDto.getLastname(), Boolean.FALSE);
		return JwtUserFactory.create(registerUser);
	}

	@PostMapping("users/admin")
	public JwtUser createUserAdmin(@RequestBody UserDTO userDto) {
		User registerUser = userService.registerUser(userDto.getUsername(), userDto.getPassword(), userDto.getEmail(),
				userDto.getFirstname(), userDto.getLastname(), Boolean.TRUE);
		return JwtUserFactory.create(registerUser);
	}

	@PutMapping("users/update/{id}")
	public UserDTO updateUser(@PathVariable(value = "id") Long id, @RequestBody UserDTO userDto) {
		return userService.updateUser(userDto);
	}

	@DeleteMapping(value = "users/delete/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id) {
		userService.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@GetMapping("/users/authorities")
	public List<Authority> findAllAuthorities() {
		return userService.findAllAuthorities();
	}

}
