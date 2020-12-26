package es.udc.siteapp.security;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = -6823347633429573012L;

	private final String token;
}
