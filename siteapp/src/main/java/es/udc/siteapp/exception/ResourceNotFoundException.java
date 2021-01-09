package es.udc.siteapp.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7730381527585546262L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
