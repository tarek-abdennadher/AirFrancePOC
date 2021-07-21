package fr.airfrance.poc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * <p>
 *     Exception Utility class
 * </p>
 */
public class ExceptionUtility {
	
	public static ResponseEntity<ApiError> handleException(Exception e){
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage("apigateway: "+e.getMessage());
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
