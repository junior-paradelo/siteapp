package es.udc.siteapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class User extends AuditModel {

	@Id
    @Column(name = "id")
	private Long userId;
	private String name;
	private String surname;
	private String nickname;
	private String email;
	private String password;
//	private typeUserAuthority;
}
