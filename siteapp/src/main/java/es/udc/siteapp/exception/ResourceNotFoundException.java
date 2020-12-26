package es.udc.siteapp.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7730381527585546262L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
