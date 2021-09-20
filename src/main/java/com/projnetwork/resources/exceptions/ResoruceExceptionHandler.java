package com.projnetwork.resources.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.projnetwork.exceptions.DataIntegrityException;
import com.projnetwork.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResoruceExceptionHandler {
	
	//captura a exceção e a trata
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e){
		
		StandardError erro=new StandardError(HttpStatus.NOT_FOUND.value(),e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
		
}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataViolation(DataIntegrityException e){
		
		StandardError erro=new StandardError(HttpStatus.BAD_REQUEST.value(),e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		
}

}
