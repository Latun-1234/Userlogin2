package in.user.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ExceptionInfo> handleRnfe(ResourceNotFoundException rnfe){
		ExceptionInfo info=new ExceptionInfo();
		info.setCode("EX0011");
		info.setMsg(rnfe.getMessage());
		info.setDate(LocalDateTime.now());
		
		return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST); 
	}
	
	//generic 
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ExceptionInfo> handleException(Exception ex){
		ExceptionInfo info=new ExceptionInfo();
		info.setCode("EX0012");
		info.setMsg(ex.getMessage());
		info.setDate(LocalDateTime.now());
		
		return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST); 
	}
	
	

}
