package es.udc.siteapp.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.siteapp.exception.ResourceNotFoundException;
import es.udc.siteapp.model.User;
import es.udc.siteapp.repository.UserRepository;
import es.udc.siteapp.security.JwtUser;
import es.udc.siteapp.service.dto.SiteDTO;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<JwtUser> findAll() {
		List<User> findAll = userRepository.findAll();
		List<JwtUser> jwtUserList = new LinkedList<>();
		for (int i = 0; i < findAll.size(); i++) {
			User user = findAll.get(i);
			JwtUser jwtUser = new JwtUser(user);
			jwtUserList.add(jwtUser);
		}
		return jwtUserList;
	}

	public User registerUser(JwtUser jwtUser) {
		User user = new User(jwtUser);
		return userRepository.save(user);
	}

	public JwtUser getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		return new JwtUser(user);
	}

	public JwtUser getUserByUsername(String name) {
		User user = userRepository.findByUsername(name);
		if (user == null) {
			throw new ResourceNotFoundException("User not found with name: " + name);
		}
		return new JwtUser(user);
	}

	public User getCompleteUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Complete user not found with id: " + id));
	}

	public JwtUser updateUser(JwtUser jwtUser) {
		JwtUser jwtUserResponse = null;
		User user = userRepository.findById(jwtUser.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + jwtUser.getId()));

		user.setFirstname(jwtUser.getFirstname());
		user.setLastname(jwtUser.getLastname());
		user.setEmail(jwtUser.getEmail());
		User userSave = userRepository.save(user);
		jwtUserResponse = new JwtUser(userSave);

		return jwtUserResponse;
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	public List<SiteDTO> findFavourited(Long id) {
		return null;
	}

}
