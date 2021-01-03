package es.udc.siteapp.service.dto;

import es.udc.siteapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Long id;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private String email;
	private Boolean enabled;

	public UserDTO(User user) {
		this.id = user.getUserId();
		this.username = user.getUsername();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.email = user.getEmail();
		this.enabled = user.getEnabled();
	}
}
