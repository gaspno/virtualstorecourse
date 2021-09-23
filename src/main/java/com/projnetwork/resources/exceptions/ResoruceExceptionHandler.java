package com.projnetwork.resources.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
		
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> NotValide(MethodArgumentNotValidException e){
		
		ValidationError erro=new ValidationError(HttpStatus.BAD_REQUEST.value(),"erro de validação", System.currentTimeMillis());
		for(FieldError x: e.getBindingResult().getFieldErrors()) {
			erro.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		
}

}
