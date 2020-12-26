package es.udc.siteapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.siteapp.model.User;
import es.udc.siteapp.security.JwtTokenUtil;
import es.udc.siteapp.security.JwtUser;
import es.udc.siteapp.service.UserService;

@RestController
@RequestMapping("/api/")
public class UserController {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	UserService userService;

	@GetMapping("users")
	public List<JwtUser> findAllUsers() {
		return userService.findAll();
	}

	@GetMapping("users/{id}")
	public JwtUser getUserById(@PathVariable(value = "id") Long id) {
		return userService.getUserById(id);
	}

	@GetMapping("users/name={name}")
	public JwtUser getUserByName(@PathVariable(value = "name") String name) {
		return userService.getUserByUsername(name);
	}

	@PostMapping("users")
	public JwtUser createUser(@RequestBody JwtUser jwtUser) {
		User registerUser = userService.registerUser(jwtUser);
		return new JwtUser(registerUser);
	}

	@PutMapping("users/{id}")
	public JwtUser updateUser(@PathVariable(value = "id") Long id, @RequestBody JwtUser jwtUser) {
		return userService.updateUser(jwtUser);
	}

	@DeleteMapping(value = "users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id) {
		userService.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@GetMapping(value = "user")
	public JwtUser getAuthenticatedUser(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
		return user;
	}
}
