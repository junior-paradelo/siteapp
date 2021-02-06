package es.udc.siteapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.udc.siteapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByUsernameContainingIgnoreCase(String username);

	User findByUsername(String username);

	@Modifying
	@Query("from User where id = :userId")
	User findByUserId(Long userId);

}
