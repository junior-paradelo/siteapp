package es.udc.siteapp.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
