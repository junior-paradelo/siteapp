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
	private String name;
	private String surname;
	private String nickname;
	private String email;
	private String password;

	public UserDTO(User user) {
		id = user.getUserId();
		name = user.getName();
		surname = user.getSurname();
		nickname = user.getNickname();
		email = user.getEmail();
		password = user.getPassword();
	}
}
