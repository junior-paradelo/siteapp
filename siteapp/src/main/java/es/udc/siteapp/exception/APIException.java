package es.udc.siteapp.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class APIException extends RuntimeException {

	private static final long serialVersionUID = 694659546942201670L;

	public APIException(String message) {
		super(message);
	}
}
