package es.udc.siteapp.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import es.udc.siteapp.model.Authority;
import es.udc.siteapp.model.User;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static JwtUser create(User user) {
		return new JwtUser(user.getUserId(), user.getUsername(), user.getFirstname(), user.getLastname(),
				user.getEmail(), user.getPassword(), mapToGrantedAuthorities(user.getAuthorities()), user.getEnabled(),
				user.getLastPasswordResetDate());
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
		return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
				.collect(Collectors.toList());
	}
}
