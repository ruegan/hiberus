package com.heroesApi.utils.controlErrores;
 
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.heroesApi.utils.controlErrores.exception.DataNotFoundException;
 
@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass()); 
	

    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(DataNotFoundException ex, WebRequest request) {
        List<String> detalles = new ArrayList<>();
        detalles.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Datos no encontrados", detalles);
        detalles.forEach(str -> log.error(str));
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> detalles = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
        	detalles.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", detalles);
        detalles.forEach(str -> log.error(str));
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}