package es.udc.siteapp.security;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationRequest implements Serializable {

	private static final long serialVersionUID = -5382450904955376539L;

	private String username;
	private String password;
}
