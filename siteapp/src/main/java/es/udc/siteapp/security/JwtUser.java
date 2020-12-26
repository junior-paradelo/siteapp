package es.udc.siteapp.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.udc.siteapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtUser implements UserDetails {

	private static final long serialVersionUID = 3660039936883122129L;

	private final Long id;
	private final String username;
	private final String firstname;
	private final String lastname;
	private final String password;
	private final String email;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;
	private final Date lastPasswordResetDate;

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public JwtUser(User user) {
		super();
		this.id = user.getUserId();
		this.username = user.getUsername();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.authorities = (Collection<? extends GrantedAuthority>) user.getAuthorities();
		this.enabled = user.getEnabled();
		this.lastPasswordResetDate = user.getLastPasswordResetDate();
	}

}
