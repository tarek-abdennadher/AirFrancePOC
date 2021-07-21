package fr.airfrance.poc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionUtility {
	
	public static ResponseEntity<ApiError> handleException(Exception e){
		if(e.getMessage().contains("401")){
			ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED);
			apiError.setMessage("user-management: expired or invalid token" );
			return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
		}
		else{
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage("apigateway: "+e.getMessage());
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
