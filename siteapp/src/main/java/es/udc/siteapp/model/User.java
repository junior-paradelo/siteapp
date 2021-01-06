package es.udc.siteapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import es.udc.siteapp.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends AuditModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
	@SequenceGenerator(name = "users_seq_gen", sequenceName = "user_id_seq", allocationSize = 1)
	private Long userId;

	@Column(length = 50, unique = true, nullable = false)
	private String username;

	@Column(length = 100, nullable = false)
	private String password;

	@Column(length = 50, nullable = false)
	private String firstname;

	@Column(length = 50, nullable = false)
	private String lastname;

	@Column(length = 50, nullable = false)
	private String email;

	@Column(nullable = false)
	private Boolean enabled;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastPasswordResetDate;

	@ManyToOne
	@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")
	private Authority authority;

	// Base64.getEncoder().encodeToString(file.getBytes())
	@Column(columnDefinition = "text")
	private String image;

	public User(UserDTO userDto) {
		this.email = userDto.getEmail();
		this.username = userDto.getUsername();
		this.password = userDto.getPassword();
		this.firstname = userDto.getFirstname();
		this.lastname = userDto.getLastname();
	}

	public User(String username, String password, String firstname, String lastname, String email, Date createdAt,
			Date lastPasswordResetDate, Authority authority) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.enabled = true;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.setCreatedAt(createdAt);
		this.authority = authority;

	}

}
