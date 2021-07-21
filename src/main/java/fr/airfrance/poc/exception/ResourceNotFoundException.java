package fr.airfrance.poc.exception;

/**
 * <p>
 *     This is a custom exception for resource not found
 * </p>
 *
 * @author TarekAbdennadher
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8878526610645207217L;

	public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
