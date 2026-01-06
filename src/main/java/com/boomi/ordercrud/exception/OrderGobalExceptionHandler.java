package com.boomi.ordercrud.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderGobalExceptionHandler {

	
	private static final Logger log = LoggerFactory.getLogger(OrderGobalExceptionHandler.class);
	
	@ExceptionHandler(PropertiesNotFoundExcption.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(PropertiesNotFoundExcption ex){
		log.error("OrderGobalExceptionHandler: Inside the PropertiesNotFoundExcption");
		
		Map<String, String> errors = new HashMap<>();
		errors.put("error", ex.getMessage());
	
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST); 
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
		
		log.error("OrderGobalExceptionHandler: Inside the MethodArgumentNotValidException");
		
		Map<String, String> errors = new HashMap<>();
		
		ex.getAllErrors().forEach( error -> {
		                            if(error instanceof FieldError fe) {
		                            
		                errors.put(fe.getField(), fe.getDefaultMessage());   
		                         }
		});
		
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST); 
	}
	
}
