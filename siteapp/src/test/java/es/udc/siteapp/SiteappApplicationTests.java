package es.udc.siteapp;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import es.udc.siteapp.model.User;
import es.udc.siteapp.repository.UserRepository;

@SpringBootTest
class SiteappApplicationTests {

	@Autowired
	private UserRepository userDAO;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Test
	public void createUserTest() {
		User user = new User("Junior", "Paradelo", "jparadelo", "jparadelo@gmail.com", bcrypt.encode("1234"));
		User returnUser = userDAO.save(user);
		assertTrue(returnUser.getPassword().equalsIgnoreCase(user.getPassword()));
	}

}
