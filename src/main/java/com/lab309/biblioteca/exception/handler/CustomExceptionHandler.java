package com.lab309.biblioteca.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lab309.biblioteca.exception.NotFoundException;
import com.lab309.biblioteca.exception.ObjetoInvalidoException;
import com.lab309.biblioteca.model.CustomError;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleUsuarioNotFoundException(NotFoundException exception) {
        return new ResponseEntity<>(new CustomError(exception.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(ObjetoInvalidoException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(ObjetoInvalidoException exception, WebRequest request) {
        return new ResponseEntity<>(new CustomError(exception.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}
