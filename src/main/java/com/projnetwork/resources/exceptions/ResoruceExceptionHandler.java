package com.projnetwork.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.projnetwork.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResoruceExceptionHandler {
	
	//captura a exceção e a trata
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e,HttpServletRequest request){
		
		StandardError erro=new StandardError(HttpStatus.NOT_FOUND.value(),e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
		
}

}
