package com.lab309.biblioteca.exception;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException{
	
	public NotFoundException(String message) {
        super(message);
    }
}
