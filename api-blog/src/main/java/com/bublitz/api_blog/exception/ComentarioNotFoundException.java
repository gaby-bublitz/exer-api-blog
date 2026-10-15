package com.bublitz.api_blog.exception;

public class ComentarioNotFoundException extends ResourceNotFoundException {
	private static final long serialVersionUID = 1L;
	
	public ComentarioNotFoundException(String message) {
		super(message);
	}

}
