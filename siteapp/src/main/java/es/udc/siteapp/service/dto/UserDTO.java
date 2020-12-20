package es.udc.siteapp.service.dto;

import es.udc.siteapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private String name;
	private String surname;
	private String nickname;
	private String email;

	public UserDTO(User user) {
		name = user.getName();
		surname = user.getSurname();
		nickname = user.getNickname();
		email = user.getEmail();
	}
}
