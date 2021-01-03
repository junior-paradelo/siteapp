package es.udc.siteapp.security;

import java.util.LinkedList;
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
				user.getPassword(), user.getEmail(), mapToGrantedAuthorities(user.getAuthority()), user.getEnabled(),
				user.getLastPasswordResetDate());
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(Authority authority) {
		List<Authority> list = new LinkedList<>();
		list.add(authority);
		return list.stream().map(authoritie -> new SimpleGrantedAuthority(authoritie.getName().name()))
				.collect(Collectors.toList());
	}
}
