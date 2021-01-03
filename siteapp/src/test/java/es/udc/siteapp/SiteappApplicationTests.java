package es.udc.siteapp;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import es.udc.siteapp.repository.UserRepository;

@SpringBootTest
class SiteappApplicationTests {

	@Autowired
	private UserRepository userDAO;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Autowired
	private EntityManager entityManager;

	@Test
	void createUserTest() {

	}

}
