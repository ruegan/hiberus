package com.heroesApi.utils.controlErrores.exception;
 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException 
{
    public DataNotFoundException(String exception) {
        super(exception);
    }
}