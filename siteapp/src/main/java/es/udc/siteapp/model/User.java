package es.udc.siteapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class User extends AuditModel {

	private Long userId;
	private String name;
	private String surname;
	private String nickname;
	private String email;
	private String password;
//	private typeUserAuthority;
}
