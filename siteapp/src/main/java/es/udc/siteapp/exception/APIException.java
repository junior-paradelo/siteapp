package es.udc.siteapp.exception;

import lombok.Data;

@Data
public class APIException extends RuntimeException {
	public APIException(String message) {
		super(message);
	}
}
