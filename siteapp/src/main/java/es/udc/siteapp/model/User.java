package es.udc.siteapp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends AuditModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
	@SequenceGenerator(name = "users_seq_gen", sequenceName = "users_id_seq")
	private Long userId;
	private String name;
	private String surname;
	@Column(unique = true)
	private String nickname;
	private String email;
	private String password;
	@Column(name = "type")
	private UserAuthority typeUserAuthority;
	@Column(nullable = false)
	private boolean admin;
	@Column(nullable = false)
	private boolean active;

	public User(String name, String surname, String nickname, String email, String password,
			UserAuthority typeUserAuthority, boolean admin) {
		super();
		this.name = name;
		this.surname = surname;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.typeUserAuthority = typeUserAuthority;
		this.admin = admin;
		this.active = true;
		this.setCreatedAt(new Timestamp(System.currentTimeMillis()));
	}

}
