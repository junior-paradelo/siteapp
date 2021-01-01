package es.udc.siteapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import es.udc.siteapp.security.JwtUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(callSuper = false)
public class User extends AuditModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
	@SequenceGenerator(name = "users_seq_gen", sequenceName = "users_id_seq", allocationSize = 1)
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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_AUTHORITY", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID") })
	private List<Authority> authorities;

	public User(JwtUser jwtUser) {
		username = jwtUser.getUsername();
		password = jwtUser.getPassword();
		email = jwtUser.getEmail();
		firstname = jwtUser.getFirstname();
		lastname = jwtUser.getLastname();
		enabled = true;
		lastPasswordResetDate = jwtUser.getLastPasswordResetDate();
//		authorities = jwtUser.getAuthorities();
	}

	public User(String username, String password, String firstname, String lastname, String email,
			Date lastPasswordResetDate, List<Authority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.enabled = true;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.authorities = authorities;
	}

}
