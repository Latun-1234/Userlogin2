package in.user.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
	
		
	}

	

	public ResourceNotFoundException(String message) {
		super(message);
		
	}
}
